package br.com.meinz.agentms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "flow_execution")
@NoArgsConstructor
@AllArgsConstructor
public class FlowExecution extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "flow_id")
    private Flow flow;

    @OneToMany(mappedBy = "flowExecution")
    private Set<FlowExecutionOccurrence> flowExecutionOccurrences = new LinkedHashSet<>();

    @Column(name = "slug", nullable = false, unique = true)
    private String slug;

    @PrePersist
    public void prePersist() {
        this.slug = UUID.randomUUID().toString();
    }
}
