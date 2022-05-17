package scheduler;

import dataModels.HTTPTask;
import org.quartz.SchedulerException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {

    private HTTPTask httpTask;

    public void setConnection() throws IOException {

        String headerKey = this.httpTask.getHeaders().keySet().toString();

        URL url = new URL(this.httpTask.getUrl());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty(headerKey, this.httpTask.getHeaders().get(headerKey));
        con.setRequestMethod(this.httpTask.getMethod());
    }

    public void setHttpTask(HTTPTask httpTask) {
        this.httpTask = httpTask;
    }
}
