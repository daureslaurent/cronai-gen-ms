package lda.cronai.gen.domain.tools.service;

import jakarta.annotation.PostConstruct;
import lda.cronai.gen.domain.tools.model.ToolsAbstract;
import lda.cronai.gen.domain.tools.port.ToolsInput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class ToolsService implements ToolsInput {

    private final Set<ToolsAbstract> tools;

    @PostConstruct
    public void init() {
      log.info("🔵🔵🟠 Init of tool service {}", tools.size());
      tools.forEach(tool -> log.info("🔵🔵🔵 Tool: {}", tool.getId()));
    }

    @Override
    public List<ToolsAbstract> getTools() {
        return List.copyOf(tools);
    }
}
