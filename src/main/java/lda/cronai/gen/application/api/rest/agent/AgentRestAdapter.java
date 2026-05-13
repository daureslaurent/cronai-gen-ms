package lda.cronai.gen.application.api.rest.agent;

import lda.cronai.gen.application.api.rest.agent.mapper.AgentRestMapper;
import lda.cronai.gen.application.api.rest.agent.model.AgentRequest;
import lda.cronai.gen.application.api.rest.agent.model.AgentResponse;
import lda.cronai.gen.domain.agent.model.Agent;
import lda.cronai.gen.domain.agent.port.AgentInput;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agent")
public class AgentRestAdapter {

    private final AgentInput agentInput;
    private final AgentRestMapper mapper;

    @GetMapping
    public ResponseEntity<Page<AgentResponse>> getAllAgents(Pageable pageable) {
        return ResponseEntity.ok(
                agentInput.findAll(pageable)
                        .map(mapper::toResponse)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<AgentResponse>> getAgentById(@PathVariable String id) {
        Optional<AgentResponse> agent = agentInput
                .findById(id)
                .map(mapper::toResponse);
        return ResponseEntity
                .status(agent.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(agent);
    }

    @PostMapping
    public ResponseEntity<AgentResponse> createAgent(@RequestBody AgentRequest requestDTO) {
        final Agent agent = mapper.toDomain(requestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toResponse(agentInput.create(agent)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgentResponse> updateAgent(@PathVariable String id, @RequestBody AgentRequest requestDTO) {
        final Agent agent = mapper.toDomain(requestDTO);
        return ResponseEntity
                .ok(mapper.toResponse(agentInput.update(id, agent)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgent(@PathVariable String id) {
        boolean deleted = agentInput.delete(id);
        return ResponseEntity
                .status(deleted ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND)
                .build();
    }
}
