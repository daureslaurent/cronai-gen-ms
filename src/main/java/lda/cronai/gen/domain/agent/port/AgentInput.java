package lda.cronai.gen.domain.agent.port;

import lda.cronai.gen.domain.agent.model.Agent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AgentInput {
    Agent create(Agent request);
    Agent update(String id, Agent request);
    Optional<Agent> findById(String id);
    Page<Agent> findAll(Pageable pageable);
    boolean delete(String id);
}