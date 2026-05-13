package lda.cronai.gen.application.api.rest.tools;

import lda.cronai.gen.application.api.rest.tools.mapper.ToolsRestMapper;
import lda.cronai.gen.application.api.rest.tools.model.ToolsResponse;
import lda.cronai.gen.domain.tools.port.ToolsInput;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tool")
@RequiredArgsConstructor
public class ToolsRestAdapter {

    private final ToolsInput toolInput;
    private final ToolsRestMapper mapper;

    @GetMapping
    public List<ToolsResponse> getTools() {
        return toolInput.getTools().stream()
                .map(mapper::toResponse)
                .toList();
    }

}
