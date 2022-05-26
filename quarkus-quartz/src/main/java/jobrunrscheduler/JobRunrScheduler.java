package jobrunrscheduler;

import dataModels.Delay;
import org.jobrunr.jobs.Job;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.storage.nosql.mongo.MongoDBStorageProvider;
import org.quartz.SchedulerException;

import javax.inject.Inject;

public class JobRunrScheduler {
    private Delay delay;
    private MongoDBStorageProvider mongoDBStorageProvider = new MongoDBStorageProvider("localhost", 21017);

    @Inject
    JobClassJobRunr jobClassJobRunr;

    @Inject
    JobScheduler jobScheduler;

    public void setDelay(Delay delay) {
        this.delay = delay;
    }
    public JobRunrScheduler() throws SchedulerException {}

    //instance
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
    }


    public void activateScheduler() throws InterruptedException {
        /*JobRunr.configure()
                .useStorageProvider(new InMemoryStorageProvider())
                .useBackgroundJobServer()
                .useDashboard()
                .initialize();*/

        jobScheduler.<JobClassJobRunr>enqueue(jobClassJobRunr -> jobClassJobRunr.doRecurringJob());

        //Thread.currentThread().join();
    }

    public void storeJobData(Job job){
        mongoDBStorageProvider.save(job);
    }
}
