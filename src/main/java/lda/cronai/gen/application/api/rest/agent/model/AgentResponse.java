package lda.cronai.gen.application.api.rest.agent.model;

import lombok.Builder;

@Builder
public record AgentResponse (
    String id,
    String name,
    String description,
    String type,
    String status,
    Long totalCount
) { }