package scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JobClass implements Job {

    @Override
    public void execute(JobExecutionContext context){
        System.out.println("HelloWorld!");
    }
}