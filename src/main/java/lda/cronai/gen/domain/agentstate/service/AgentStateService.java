package lda.cronai.gen.domain.agentstate.service;

import lda.cronai.gen.domain.agent.model.Agent;
import lda.cronai.gen.domain.agentstate.model.AgentState;
import lda.cronai.gen.domain.agentstate.port.AgentStateInput;
import lda.cronai.gen.domain.agentstate.port.AgentStatePersistenceOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgentStateService implements AgentStateInput {

    private final AgentStatePersistenceOutput persistenceOutput;

    @Override
    public String create(Agent agent) {

        persistenceOutput.save(AgentState.builder().build());

        return "";
    }
}
