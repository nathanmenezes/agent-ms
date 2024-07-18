package br.com.meinz.agentms.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "flow_execution_occurrence")
public class FlowExecutionOccurrence extends AbstractEntity {

    @Column()

    @ManyToOne(optional = false)
    @JoinColumn(name = "flow_execution_id", nullable = false)
    private FlowExecution flowExecution;

}
