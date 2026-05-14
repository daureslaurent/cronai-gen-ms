package lda.cronai.gen.infra.runner;

import lda.cronai.gen.domain.JobRunnerOutput;
import lda.cronai.gen.domain.agent.port.AgentRunnerInput;
import lombok.RequiredArgsConstructor;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JobRunnerAdapter implements JobRunnerOutput {

    private final JobScheduler jobScheduler;
    private final AgentRunnerInput agentRunnerInput;

    @Override
    public String addCronJob(String id, String cron) {
        return jobScheduler.scheduleRecurrently(id, cron, () -> agentRunnerInput.runAgent(id));
    }

    @Override
    public void deleteCronJob(String id) {
        jobScheduler.deleteRecurringJob(id);
    }
}
