package lda.cronai.gen.domain.agent.service;

import lda.cronai.gen.domain.JobRunnerOutput;
import lda.cronai.gen.domain.agent.model.Agent;
import lda.cronai.gen.domain.agent.port.AgentInput;
import lda.cronai.gen.domain.agent.port.AgentPersistenceOutput;
import lda.cronai.gen.domain.agent.port.AgentRunnerInput;
import lda.cronai.gen.domain.tools.port.ToolsInput;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgentService implements AgentInput {

    private static final int MAX_AGENTS_PER_USER = 5;

    private final AgentPersistenceOutput agentPersistence;
    private final ToolsInput toolsInput;
    private final JobRunnerOutput jobRunnerOutput;
    private final AgentRunnerInput agentRunnerInput;

    @Override
    public Agent create(Agent request) {

        // Validate tools selection
        if (toolsInput.idsNotValid(request.tools())) {
            throw new RuntimeException("Some tools not valid !");
        }

        // Validate CronExpression
        if (!CronExpression.isValidExpression(request.cron())) {
            throw new RuntimeException("Cron expression invalid !");
        }

        final var agentCreated = agentPersistence.save(request);
        final var runnerId = jobRunnerOutput.addCronJob(agentCreated.id(), agentCreated.cron());

        // Check if user already has max agents
//        Page<Agent> userAgents = agentPersistence.findByUserId(userId, Pageable.ofSize(MAX_AGENTS_PER_USER + 1));
        
//        if (userAgents.getTotalElements() >= MAX_AGENTS_PER_USER) {
//            throw new IllegalStateException("User already has the maximum number of agents (" + MAX_AGENTS_PER_USER + ")");
//        }
        
        return agentPersistence.save(agentCreated.toBuilder()
                .runnerId(runnerId)
                .build()
        );
    }

    @Override
    public Agent update(String id, Agent request) {
//        // Load existing agent to preserve id and timestamps
//        Agent existingAgent = agentPersistence.findById(id)
//                .orElseThrow(() -> new RuntimeException("Agent not found with id: " + id));
//
//        // Merge request data with existing agent
//        Agent updatedAgent = Agent.builder()
//                .id(existingAgent.id())
//                .name(request.name())
//                .description(request.description())
//                .type(request.type())
//                .status(request.status())
//                .createdAt(existingAgent.createdAt())
//                .updatedAt(existingAgent.updatedAt())
//                .build();
//
//        return agentPersistence.save(updatedAgent);
        return request;
    }

    @Override
    public Optional<Agent> findById(String id) {
        return agentPersistence.findById(id);
    }

    @Override
    public Page<Agent> findAll(Pageable pageable) {
        return agentPersistence.findAll(pageable);
    }

    @Override
    public boolean delete(String id) {
        return agentPersistence.deleteById(id);
    }
}