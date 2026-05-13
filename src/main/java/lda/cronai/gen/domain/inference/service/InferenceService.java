package lda.cronai.gen.domain.inference.service;

import lda.cronai.gen.domain.inference.port.InferenceInput;
import lda.cronai.gen.domain.inference.port.InferenceOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InferenceService implements InferenceInput {

    private final InferenceOutput inferenceOutput;

}
