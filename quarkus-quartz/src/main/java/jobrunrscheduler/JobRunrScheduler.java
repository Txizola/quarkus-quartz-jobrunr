package jobrunrscheduler;

import dataModels.Delay;
import dataModels.HTTPTask;
import dataModels.Schedule;
import org.jobrunr.jobs.JobId;
import org.jobrunr.scheduling.JobScheduler;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.Instant;

@ApplicationScoped
public class JobRunrScheduler implements Schedule {

    @Inject
    JobClassJobRunr jobClassJobRunr;

    @Inject
    JobScheduler jobScheduler;

    public JobRunrScheduler(){}

    public void schedule(HTTPTask httpTask, Delay delay) throws InterruptedException {
        final JobId enqueuedJobId = jobScheduler.schedule(Instant.now().plusSeconds(delay.getDelay()), () -> jobClassJobRunr.doJob(httpTask));
        System.out.println("job enqueued = " + enqueuedJobId);
    }
}
