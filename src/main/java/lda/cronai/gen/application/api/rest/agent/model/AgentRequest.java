package lda.cronai.gen.application.api.rest.agent.model;

import lombok.Builder;

import java.util.Set;

@Builder
public record AgentRequest (
    String name,
    String description,
    String cron,
    Set<String> tools
) { }
