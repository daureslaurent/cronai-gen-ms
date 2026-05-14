package lda.cronai.gen.domain.agentstate.model;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AgentState (
        String id,
        String type,
        String status,
        String agentId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) { }