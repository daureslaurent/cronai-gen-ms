package lda.cronai.gen.infra.persistence.agent.repository;

import lda.cronai.gen.infra.persistence.agent.entity.AgentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends MongoRepository<AgentEntity, String> {
    Page<AgentEntity> findByUserId(String userId, Pageable pageable);
}
