package lda.cronai.gen.application.api.rest.agent.model;

import lombok.Builder;

@Builder
public record AgentRequest (
    String name,
    String description,
    String type,
    String status,
    String userId
) { }
