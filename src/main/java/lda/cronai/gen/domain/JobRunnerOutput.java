package lda.cronai.gen.domain;

public interface JobRunnerOutput {

    String addCronJob(final String id, final String cron);
    void deleteCronJob(final String id);

}
