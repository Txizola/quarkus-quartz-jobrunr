package Scheduler;

import dataModels.RequestServices;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class QuartzScheduler {


    private Trigger trigger;

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

    SchedulerFactory schedulerFactory = new StdSchedulerFactory();
    Scheduler scheduler = schedulerFactory.getScheduler();
    JobDetail job = newJob(JobClass.class)
            .withIdentity("jobTest", "jobGroup")
            .build();

    public void ActivateScheduler() throws SchedulerException {
        scheduler.scheduleJob(job, trigger);
        scheduler.start();
    }

    public void setTrigger(Date startTime, int delay){

        trigger = newTrigger()
                .withIdentity("jobTrigger", "jobGroup")
                .startAt(startTime).withSchedule(simpleSchedule()
                        .withIntervalInSeconds(delay)
                        .repeatForever())
                .build();
    }
}