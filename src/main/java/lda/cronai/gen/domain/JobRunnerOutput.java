package lda.cronai.gen.domain;

public interface JobRunnerOutput {

    void addCronJob(final String id, final String cron);
    void deleteCronJob(final String id);

}
