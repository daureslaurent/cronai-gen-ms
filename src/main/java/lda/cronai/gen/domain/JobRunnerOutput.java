package lda.cronai.gen.domain;

import java.util.function.Function;

public interface JobRunnerOutput {

    String addCronJob(final String id, final String cron);
    void deleteCronJob(final String id);

}
