package lda.cronai.gen.infra.persistence.agentstate;

import lda.cronai.gen.domain.agentstate.model.AgentState;
import lda.cronai.gen.domain.agentstate.port.AgentStatePersistenceOutput;
import lda.cronai.gen.infra.persistence.agentstate.mapper.AgentStatePersistenceMapper;
import lda.cronai.gen.infra.persistence.agentstate.repository.AgentStateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgentStatePersistenceAdapter implements AgentStatePersistenceOutput {

    private final AgentStateRepository repository;
    private final AgentStatePersistenceMapper mapper;

    @Override
    public AgentState save(AgentState agentState) {
        final var agentStateEntity = mapper.toEntity(agentState);
        final var saved = repository.save(agentStateEntity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<AgentState> findById(String id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public boolean deleteById(String id) {
        boolean exists = repository.existsById(id);
        repository.deleteById(id);
        return exists;
    }

    @Override
    public Page<AgentState> findByAgentId(String agentId, Pageable pageable) {
        return repository.findByAgentId(agentId, pageable)
                .map(mapper::toDomain);
    }
}