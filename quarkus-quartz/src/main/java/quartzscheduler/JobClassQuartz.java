package quartzscheduler;

import httprequests.HttpRequest;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

import javax.enterprise.context.RequestScoped;
import java.io.IOException;

@RequestScoped
public class JobClassQuartz implements Job {

    @Override
    public void execute(JobExecutionContext context){
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        String url = dataMap.getString("url");
        String method = dataMap.getString("method");
        String header = dataMap.getString("headerKey");
        String headerValue = dataMap.getString("headerValue");

        try {
            HttpRequest httpRequest = new HttpRequest();
            httpRequest.setConnection(url, method, header, headerValue);
            System.out.println("Quartz URL connection: " + url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}