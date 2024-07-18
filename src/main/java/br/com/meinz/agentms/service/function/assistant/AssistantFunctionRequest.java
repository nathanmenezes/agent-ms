package br.com.meinz.agentms.service.function.assistant;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class AssistantFunctionRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -5783378589226971051L;

    private String apiKey;
    private String assistantId;
    private String prompt;
    private String additionalInstruction;
    private String expectedOutput;

}
