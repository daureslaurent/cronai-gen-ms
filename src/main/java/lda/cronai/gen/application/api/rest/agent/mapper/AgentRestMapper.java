package lda.cronai.gen.application.api.rest.agent.mapper;

import lda.cronai.gen.application.api.rest.agent.model.AgentRequest;
import lda.cronai.gen.application.api.rest.agent.model.AgentResponse;
import lda.cronai.gen.domain.agent.model.Agent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgentRestMapper {
    Agent toDomain(final AgentRequest request);
    AgentResponse toResponse(final Agent agent);
}
