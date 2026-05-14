package lda.cronai.gen.domain.agent.service;

import lda.cronai.gen.domain.agent.port.AgentInput;
import lda.cronai.gen.domain.agent.port.AgentRunnerInput;
import lda.cronai.gen.domain.agentstate.port.AgentStateInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgentRunnerService implements AgentRunnerInput {

    private final AgentInput agentInput;
    private final AgentStateInput agentStateInput;

    @Override
    public void runAgent(String agentId) {
        final var agent = agentInput.findById(agentId)
                .orElseThrow(() ->
                        new RuntimeException("Agent %s not found !".formatted(agentId))
                );
        agentStateInput.create(agent);
    }
}
