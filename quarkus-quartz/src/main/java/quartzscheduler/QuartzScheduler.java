package quartzscheduler;

import dataModels.Delay;
import dataModels.HTTPTask;
import dataModels.Schedule;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.util.Date;
import java.util.UUID;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

@ApplicationScoped
public class QuartzScheduler implements Schedule {

    SchedulerFactory schedulerFactory = new StdSchedulerFactory();
    Scheduler scheduler;


    public QuartzScheduler() throws SchedulerException {
        scheduler = schedulerFactory.getScheduler();
        scheduler.start();
    }

    public void schedule(HTTPTask httpTask, Delay delay) throws SchedulerException {
        String headerKey = httpTask.getHeaders().keySet().toString();
        String triggerID = UUID.randomUUID().toString();
        JobDetail job = newJob(JobClassQuartz.class)
                .withIdentity(triggerID, "jobGroupTest")
                .usingJobData("url", httpTask.getUrl())
                .usingJobData("method", httpTask.getMethod())
                .usingJobData("headerKey", headerKey)
                .usingJobData("headerValue", httpTask.getHeaders().get(headerKey))
                .build();

        Date startTimeDate = new Date(System.currentTimeMillis() + delay.getDelay()*1000);
        Trigger trigger = newTrigger()
                .withIdentity(triggerID, "triggerGroupTest")
                .startAt(startTimeDate)
                .build();

        scheduler.scheduleJob(job, trigger);
    }

}