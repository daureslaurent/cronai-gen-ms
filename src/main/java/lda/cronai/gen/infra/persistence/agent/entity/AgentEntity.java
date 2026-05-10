package lda.cronai.gen.infra.persistence.agent.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Agent entity representing a single agent in the system.
 */
@Data
@Builder
@Document(collection = "agents")
public class AgentEntity {

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    private String description;

    private String type;

    private String status;

    private String userId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}