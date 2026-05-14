package lda.cronai.gen.infra.persistence.agentstate.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "agent_state")
public class AgentStateEntity {

    @Id
    private String id;

    private String name;

    private String description;

    private String type;

    private String status;

    @Indexed
    private String agentId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
