package lda.cronai.gen.infra.stream.agent;

import org.springframework.context.annotation.Bean;

import java.util.function.Function;

public class StreamAgent {

    @Bean
    public Function<String, String> input() {
        return value -> "";
    }

}
