package jobrunrscheduler;

import dataModels.Delay;
import dataModels.HTTPTask;
import dataModels.Schedule;
import org.jobrunr.jobs.JobId;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.storage.StorageProvider;
import org.jobrunr.storage.nosql.mongo.MongoDBStorageProvider;
import org.quartz.SchedulerException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;


public class JobRunrScheduler implements Schedule {

    @Inject
    JobClassJobRunr jobClassJobRunr;

    @Inject
    JobScheduler jobScheduler;

    public JobRunrScheduler() throws SchedulerException {}

    /*instance
    private static JobRunrScheduler jobRunrScheduler;
    static {
        try {
            jobRunrScheduler = new JobRunrScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    public static JobRunrScheduler getInstance() {
        return jobRunrScheduler;
    }*/


    public void schedule(HTTPTask httpTask, Delay delay) throws InterruptedException {
        final JobId enqueuedJobId = jobScheduler.enqueue(() -> jobClassJobRunr.doJob(httpTask));
        System.out.println("job enqueued = " + enqueuedJobId);
    }
}
