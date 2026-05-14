package lda.cronai.gen.infra.persistence.agentstate.mapper;

import lda.cronai.gen.domain.agentstate.model.AgentState;
import lda.cronai.gen.infra.persistence.agentstate.entity.AgentStateEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgentStatePersistenceMapper {
    AgentStateEntity toEntity(final AgentState agentState);
    AgentState toDomain(final AgentStateEntity entity);
}
