package lda.cronai.gen.domain.tools.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class ToolsAbstract {
    protected final String id;
    protected final String name;
    protected final String description;
}
