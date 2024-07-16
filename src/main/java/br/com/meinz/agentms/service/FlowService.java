package br.com.meinz.agentms.service;

import br.com.meinz.agentms.dto.request.FlowRequest;
import br.com.meinz.agentms.model.Flow;
import br.com.meinz.agentms.repository.FlowRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class FlowService {

    private final FlowRepository flowRepository;
    private final FunctionService functionService;

    public void createFlow(FlowRequest flowRequest) {
        log.info("Creating flow: {}", flowRequest);
        Flow flow = new Flow(flowRequest);
        flowRepository.save(flow);
    }
}
