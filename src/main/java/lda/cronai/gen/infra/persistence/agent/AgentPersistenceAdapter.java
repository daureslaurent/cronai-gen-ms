package lda.cronai.gen.infra.persistence.agent;

import lda.cronai.gen.domain.model.Agent;
import lda.cronai.gen.domain.port.AgentPersistenceOutput;
import lda.cronai.gen.infra.persistence.agent.mapper.AgentPersistenceMapper;
import lda.cronai.gen.infra.persistence.agent.repository.AgentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgentPersistenceAdapter implements AgentPersistenceOutput {

    private final AgentRepository repository;
    private final AgentPersistenceMapper mapper;

    @Override
    public Agent save(Agent agent) {
        final var entity = mapper.toEntity(agent);

        return mapper.toDomain(
                repository.save(entity)
        );
    }

    @Override
    public Optional<Agent> findById(String id) {
        return repository
                .findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Page<Agent> findAll(Pageable pageable) {
        return repository
                .findAll(pageable)
                .map(mapper::toDomain);
    }

    @Override
    public boolean deleteById(String id) {
        boolean exists = repository.existsById(id);
        repository.deleteById(id);
        return exists;
    }

    @Override
    public Page<Agent> findByUserId(String userId, Pageable pageable) {
        return repository
                .findByUserId(userId, pageable)
                .map(mapper::toDomain);
    }
}
