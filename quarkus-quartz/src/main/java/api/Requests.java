package api;

import dataModels.Order;
import jobrunrscheduler.JobRunrScheduler;
import org.quartz.Job;
import org.quartz.SchedulerException;
import quartzscheduler.QuartzScheduler;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

@Path("/schedule")
@ApplicationScoped
public class Requests {

    private QuartzScheduler quartzScheduler = QuartzScheduler.getInstance();
    private JobRunrScheduler jobRunrScheduler;// = JobRunrScheduler.getInstance();
    private Set<Order> orders = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    @Inject
    public Requests(JobRunrScheduler jobRunrScheduler) throws SchedulerException {
        this.jobRunrScheduler = jobRunrScheduler;
    }

    @GET
    public Set<Order> list() {
            return orders;
        }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Set<Order> add(Order order) throws SchedulerException, IOException {
        orders.add(order);
        quartzScheduler.schedule(order.getTask(), order.getDelay());
        return orders;
    }

    @Path("/jobrunr")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Set<Order> addJobRunr(Order order) throws IOException, InterruptedException {
        orders.add(order);
        jobRunrScheduler.schedule(order.getTask(), order.getDelay());
        return orders;
    }

}