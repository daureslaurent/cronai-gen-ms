package lda.cronai.gen.domain.agentstate.port;

import lda.cronai.gen.domain.agent.model.Agent;

public interface AgentStateInput {
    String create(final Agent agent);
}
