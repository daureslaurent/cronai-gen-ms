package lda.cronai.gen.domain.port;

import lda.cronai.gen.domain.model.Agent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AgentInput {

    /**
     * Create a new agent.
     *
     * @param request the agent request DTO
     * @return the created agent
     */
    Agent create(Agent request);

    /**
     * Update an existing agent.
     *
     * @param id the agent ID
     * @param request the agent request DTO with updates
     * @return the updated agent
     */
    Agent update(String id, Agent request);

    /**
     * Find an agent by ID.
     *
     * @param id the agent ID
     * @return an optional containing the agent or empty if not found
     */
    Optional<Agent> findById(String id);

    /**
     * Find all agents.
     *
     * @return a page of all agents
     */
    Page<Agent> findAll(Pageable pageable);

    /**
     * Delete an agent by ID.
     *
     * @param id the agent ID
     * @return true if deleted, false otherwise
     */
    boolean delete(String id);

}