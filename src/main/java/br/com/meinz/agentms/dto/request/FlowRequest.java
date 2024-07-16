package br.com.meinz.agentms.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FlowRequest {
    @NotBlank
    private String name;
    private String description;

}
