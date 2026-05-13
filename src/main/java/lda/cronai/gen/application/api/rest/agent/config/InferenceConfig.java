package lda.cronai.gen.application.api.rest.agent.config;

import lda.cronai.gen.domain.inference.port.InferenceOutput;
import lda.cronai.gen.infra.provider.ollama.OllamaInferenceAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InferenceConfig {

    @Bean
    InferenceOutput inferenceOutput() {
        return new OllamaInferenceAdapter();
    }

}
