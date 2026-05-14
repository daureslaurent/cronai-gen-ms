package lda.cronai.gen.infra.persistence.agentstate.repository;

import lda.cronai.gen.infra.persistence.agentstate.entity.AgentStateEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentStateRepository extends MongoRepository<AgentStateEntity, String> {
    Page<AgentStateEntity> findByAgentId(String agentId, Pageable pageable);
}
