package br.com.meinz.agentms.resource;

import br.com.meinz.agentms.dto.request.FlowRequest;
import br.com.meinz.agentms.service.FlowService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/flow")
public class FlowResource {

    private final FlowService flowService;

    @PostMapping
    public DeferredResult<ResponseEntity<Void>> createFlow(@RequestBody @Valid FlowRequest flowRequest) {
        final DeferredResult<ResponseEntity<Void>> deferredResult = new DeferredResult<>();
        flowService.createFlow(flowRequest);
        deferredResult.setResult(ResponseEntity.created(URI.create("/v1/flow")).build());
        return deferredResult;
    }
}
