package lda.cronai.gen.domain.port;

import lda.cronai.gen.domain.model.Agent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AgentPersistenceOutput {

    /**
     * Save a new agent.
     *
     * @param agent the agent to save
     * @return the saved agent
     */
    Agent save(Agent agent);

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
     * @param pageable the pagination
     * @return a page of agents
     */
    Page<Agent> findAll(Pageable pageable);

    /**
     * Delete an agent by ID.
     *
     * @param id the agent ID
     * @return true if deleted, false otherwise
     */
    boolean deleteById(String id);

    /**
     * Find agents by userId with pagination.
     *
     * @param userId the user ID
     * @param pageable the pagination
     * @return a page of agents for the given userId
     */
    Page<Agent> findByUserId(String userId, Pageable pageable);
}