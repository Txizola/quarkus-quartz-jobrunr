package quartzscheduler;

import httprequests.HttpRequest;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;

@ApplicationScoped
public class JobClassQuartz implements Job {
    private HttpRequest httpRequest = new HttpRequest();

    @Override
    public void execute(JobExecutionContext context){
        System.out.println("HelloWorld!");

        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        String url = dataMap.getString("url");
        String method = dataMap.getString("method");
        String header = dataMap.getString("headerKey");
        String headerValue = dataMap.getString("headerValue");
        System.out.println("url1: " + url);

        try {
            httpRequest.setConnection(url, method, header, headerValue);
            System.out.println("url2: " + url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}