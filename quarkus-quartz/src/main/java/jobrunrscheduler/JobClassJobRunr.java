package jobrunrscheduler;

import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.jobs.context.JobRunrDashboardLogger;
//import org.jobrunr.quarkus.annotations.Recurring;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobClassJobRunr {
    private static final Logger LOGGER = new JobRunrDashboardLogger(LoggerFactory.getLogger(JobClassJobRunr.class));

    //@Recurring(id = "my-recurring-job", cron = "0,30 0 0 ? * * *")
    @Job(name = "Random Job")
    public void doRecurringJob() {
        System.out.println("Doing some work without arguments");
    }
}
