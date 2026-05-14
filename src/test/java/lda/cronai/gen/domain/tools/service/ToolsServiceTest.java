package lda.cronai.gen.domain.tools.service;

import lda.cronai.gen.domain.tools.model.MailTools;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class ToolsServiceTest {

    @Test
    void idsNotValidWhenNoValidTools() {
        // Given
        final var toolsService = new ToolsService(Set.of(
                new MailTools()
        ));
        toolsService.init();

        // When
        final boolean isNotValid = toolsService.idsNotValid(Set.of("NO_REAL_ID"));

        // Then
        assertThat(isNotValid).isTrue();
    }

    @Test
    void idsNotValidWhenValidTools() {
        // Given
        final var toolsService = new ToolsService(Set.of(
                new MailTools()
        ));
        toolsService.init();

        // When
        final boolean isNotValid = toolsService.idsNotValid(Set.of("MAIL_TOOL"));

        // Then
        assertThat(isNotValid).isFalse();
    }

}