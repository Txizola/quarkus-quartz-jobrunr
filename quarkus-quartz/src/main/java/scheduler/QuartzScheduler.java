package scheduler;

import dataModels.Delay;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class QuartzScheduler {
    private Delay delay;
    private Trigger trigger;

    SchedulerFactory schedulerFactory = new StdSchedulerFactory();
    Scheduler scheduler;
    //setup job
    JobDetail job = newJob(JobClass.class)
            .withIdentity("jobTest", "jobGroup")
            .build();

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

    public void ActivateScheduler() throws SchedulerException {
        setTrigger();
        scheduler = schedulerFactory.getScheduler();
        scheduler.scheduleJob(job, trigger);
        scheduler.start();
    }

    public void setTrigger(){
        Date startTimeDate = new Date(Long.parseLong(delay.getStartTime().toString()));
        trigger = newTrigger()
                .withIdentity("jobTrigger", "jobGroup")
                .startAt(startTimeDate).withSchedule(simpleSchedule()
                        .withIntervalInSeconds(delay.getDelay())
                        .repeatForever())
                .build();
    }
    public void setDelay(Delay delay) {
        this.delay = delay;
    }
}