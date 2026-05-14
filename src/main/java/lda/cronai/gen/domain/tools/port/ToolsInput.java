package lda.cronai.gen.domain.tools.port;

import lda.cronai.gen.domain.tools.model.ToolsAbstract;

import java.util.List;
import java.util.Set;

public interface ToolsInput {
    List<ToolsAbstract> getTools();
    boolean idsNotValid(Set<String> toolIds);
}
