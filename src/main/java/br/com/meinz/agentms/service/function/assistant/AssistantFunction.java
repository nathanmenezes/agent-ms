package br.com.meinz.agentms.service.function.assistant;

import br.com.meinz.agentms.enums.FunctionType;
import br.com.meinz.agentms.service.function.FunctionStrategy;
import io.github.sashirestela.cleverclient.Event;
import io.github.sashirestela.openai.SimpleOpenAI;
import io.github.sashirestela.openai.common.content.ContentPart;
import io.github.sashirestela.openai.domain.assistant.*;
import io.github.sashirestela.openai.domain.assistant.events.EventName;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@AllArgsConstructor
@Slf4j
public class AssistantFunction implements FunctionStrategy<AssistantFunctionRequest, AssistantFunctionResponse> {
    @Override
    public AssistantFunctionResponse execute(AssistantFunctionRequest assistantFunctionRequest) {
        SimpleOpenAI simpleOpenAi = SimpleOpenAI.builder().apiKey(assistantFunctionRequest.getApiKey()).build();
        String threadId = simpleOpenAi.threads().create(ThreadRequest.builder().build()).join().getId();
        simpleOpenAi.threadMessages().create(threadId, ThreadMessageRequest.builder()
                .role(ThreadMessageRole.USER)
                .content(assistantFunctionRequest.getPrompt())
                .build()).join();
        Stream<Event> runStreamEvents = simpleOpenAi.threadRuns().createStream(threadId, ThreadRunRequest.builder()
                .assistantId(assistantFunctionRequest.getAssistantId())
                .additionalInstructions(assistantFunctionRequest.getAdditionalInstruction())
                .build()).join();
        String assistantResponse = getAssistantResponse(runStreamEvents, simpleOpenAi, threadId);
    }

    private String getAssistantResponse(Stream<Event> runStream, SimpleOpenAI openAI, String threadId) {
        StringBuilder response = new StringBuilder();
        runStream.forEach(event -> {
            switch (event.getName()) {
                case EventName.THREAD_RUN_CREATED:
                case EventName.THREAD_RUN_COMPLETED:
                case EventName.THREAD_RUN_REQUIRES_ACTION:
                    var run = (ThreadRun) event.getData();
                    if (run.getStatus().equals(ThreadRun.RunStatus.REQUIRES_ACTION)) {
                        var runSubmitToolStream = openAI.threadRuns()
                                .submitToolOutputStream(threadId, run.getId(), ThreadRunSubmitOutputRequest.builder()
                                        .stream(true)
                                        .build())
                                .join();
                        getAssistantResponse(runSubmitToolStream, openAI, threadId);
                    }
                    break;
                case EventName.THREAD_MESSAGE_DELTA:
                    var msgDelta = (ThreadMessageDelta) event.getData();
                    var content = msgDelta.getDelta().getContent().getFirst();
                    if (content instanceof ContentPart.ContentPartTextAnnotation textContent) {
                        System.out.print(textContent.getText().getValue());
                        response.append(textContent.getText().getValue());
                    }
                    break;
                case EventName.THREAD_MESSAGE_COMPLETED:
                    System.out.println("Thread message completed");
                    break;
                default:
                    break;
            }
        });
        return response.toString();
    }

    @Override
    public FunctionType functionType() {
        return FunctionType.OPENAI_ASSISTANT;
    }
}
