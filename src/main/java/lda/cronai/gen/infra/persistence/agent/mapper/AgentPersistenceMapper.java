package lda.cronai.gen.infra.persistence.agent.mapper;

import lda.cronai.gen.domain.model.Agent;
import lda.cronai.gen.infra.persistence.agent.entity.AgentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgentPersistenceMapper {
    AgentEntity toEntity(final Agent agent);
    Agent toDomain(final AgentEntity entity);
}
