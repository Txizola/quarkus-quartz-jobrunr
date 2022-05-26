package api;

import dataModels.Order;
import jobrunrscheduler.JobRunrScheduler;
import org.quartz.Job;
import org.quartz.SchedulerException;
import quartzscheduler.QuartzScheduler;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

@Path("/schedule")
public class Requests {

    private QuartzScheduler quartzScheduler = QuartzScheduler.getInstance();
    private JobRunrScheduler jobRunrScheduler = JobRunrScheduler.getInstance();
    private Set<Order> orders = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public Requests() throws SchedulerException {
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
        //Set the start time and delay
        quartzScheduler.setDelay(order.getDelay());
        //set the payload of the order
        quartzScheduler.setJob(order.getTask());
        //activate the job
        quartzScheduler.activateScheduler();
        return orders;
    }

    @Path("/jobrunr")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void jobrunr() throws SchedulerException, IOException, InterruptedException {
        jobRunrScheduler.activateScheduler();
    }

}