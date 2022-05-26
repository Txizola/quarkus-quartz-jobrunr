package quartzscheduler;

import dataModels.Delay;
import dataModels.HTTPTask;
import db.JobMongoDB;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class QuartzScheduler {
    private JobDetail job;
    private Delay delay;
    private Trigger trigger;

    JobMongoDB jobMongoDB = new JobMongoDB();
    SchedulerFactory schedulerFactory = new StdSchedulerFactory();
    Scheduler scheduler;


    public QuartzScheduler() throws SchedulerException {}

    //instance
    private static QuartzScheduler quartzInstance;
    static {
        try {
            quartzInstance = new QuartzScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    public static QuartzScheduler getInstance() {
        return quartzInstance;
    }

    public void setDelay(Delay delay) {
        this.delay = delay;
    }

    public Delay getDelay() {
        return delay;
    }


    public void setJob(HTTPTask httpTask){

        String headerKey = httpTask.getHeaders().keySet().toString();
         job = newJob(JobClassQuartz.class)
                .withIdentity("jobTest", "jobGroupTest")
                .usingJobData("url", httpTask.getUrl())
                .usingJobData("method", httpTask.getMethod())
                .usingJobData("headerKey", headerKey)
                .usingJobData("headerValue", httpTask.getHeaders().get(headerKey))
                .build();
        JobDataMap jobDataMap = job.getJobDataMap();

    }

    public void setTrigger(){
        Date startTimeDate = new Date(Long.parseLong(delay.getStartTime().toString()));
        trigger = newTrigger()
                .withIdentity("triggerTest", "triggerGroupTest")
                .startAt(startTimeDate).withSchedule(simpleSchedule()
                        .withIntervalInSeconds(delay.getDelay())
                        .repeatForever())
                .build();
    }

    public void activateScheduler() throws SchedulerException {
        setTrigger();
        scheduler = schedulerFactory.getScheduler();
        scheduler.scheduleJob(job, trigger);
        jobMongoDB.saveJobAndTrigger(job,trigger);
        scheduler.start();
    }




}