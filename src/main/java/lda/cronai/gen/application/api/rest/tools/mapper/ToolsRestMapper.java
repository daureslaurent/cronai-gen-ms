package lda.cronai.gen.application.api.rest.tools.mapper;

import lda.cronai.gen.application.api.rest.tools.model.ToolsResponse;
import lda.cronai.gen.domain.tools.model.ToolsAbstract;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ToolsRestMapper {
    ToolsResponse toResponse(final ToolsAbstract toolsAbstract);
}
