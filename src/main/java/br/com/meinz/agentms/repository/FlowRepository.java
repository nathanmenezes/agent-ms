package br.com.meinz.agentms.repository;

import br.com.meinz.agentms.model.Flow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlowRepository extends JpaRepository<Flow, Long> {
}
