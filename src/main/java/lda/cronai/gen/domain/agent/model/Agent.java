package lda.cronai.gen.domain.agent.model;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Agent (
        String id,
        String name,
        String description,
        String type,
        String status,
        String userId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) { }