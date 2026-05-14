package lda.cronai.gen.domain.agent.model;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record Agent (
        String id,
        String name,
        String description,
        String cron,
        Set<String> tools,

        LocalDateTime createdAt,
        LocalDateTime updatedAt
) { }