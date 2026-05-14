package lda.cronai.gen.domain.agent.model;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder(toBuilder = true)
public record Agent (
        String id,
        String name,
        String description,
        Set<String> tools,

        String cron,
        String runnerId,

        LocalDateTime createdAt,
        LocalDateTime updatedAt
) { }