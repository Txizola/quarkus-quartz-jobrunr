package jobrunrscheduler;

import dataModels.HTTPTask;
import httprequests.HttpRequest;
import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.jobs.context.JobRunrDashboardLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;

@ApplicationScoped
public class JobClassJobRunr{

    private static final Logger LOGGER = new JobRunrDashboardLogger(LoggerFactory.getLogger(JobClassJobRunr.class));


    @Job
    public void doJob(HTTPTask httpTask){
        String headerKey = httpTask.getHeaders().keySet().toString();
        String url = httpTask.getUrl();
        String method = httpTask.getMethod();
        String header = httpTask.getHeaders().keySet().toString();
        String headerValue = httpTask.getHeaders().get(headerKey);

        try {
            HttpRequest httpRequest = new HttpRequest();
            httpRequest.setConnection(url, method, header, headerValue);
            System.out.println("JobRunr URL connection: " + url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
