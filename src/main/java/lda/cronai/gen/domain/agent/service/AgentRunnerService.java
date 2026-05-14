package lda.cronai.gen.domain.agent.service;

import lda.cronai.gen.domain.agent.port.AgentPersistenceOutput;
import lda.cronai.gen.domain.agent.port.AgentRunnerInput;
import lda.cronai.gen.domain.agentstate.port.AgentStateInput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AgentRunnerService implements AgentRunnerInput {

    private final AgentPersistenceOutput agentPersistence;
    private final AgentStateInput agentStateInput;

    @Override
    public Void runAgent(String agentId) {
        log.info("Run Agent {}", agentId);
        final var agent = agentPersistence.findById(agentId)
                .orElseThrow(() ->
                        new RuntimeException("Agent %s not found !".formatted(agentId))
                );
        agentStateInput.create(agent);
        return Void.TYPE.cast(null);
    }
}
