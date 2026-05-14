package lda.cronai.gen.domain.agentstate.port;

import lda.cronai.gen.domain.agentstate.model.AgentState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AgentStatePersistenceOutput {
    AgentState save(AgentState agentState);
    Optional<AgentState> findById(String id);
    boolean deleteById(String id);
    Page<AgentState> findByAgentId(String agentId, Pageable pageable);
}
