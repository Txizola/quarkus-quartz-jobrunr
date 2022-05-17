package api;

import dataModels.Order;
import org.quartz.SchedulerException;
import scheduler.HttpRequest;
import scheduler.QuartzScheduler;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

@Path("/schedule")
public class Requests {

    private QuartzScheduler quartzScheduler = QuartzScheduler.getInstance();
    private HttpRequest httpRequest =  new HttpRequest();
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
        //set and activate the trigger on the scheduler
        quartzScheduler.setDelay(order.getDelay());
        quartzScheduler.ActivateScheduler();
        //set the payload to the http request
        httpRequest.setHttpTask(order.getTask());
        return orders;
    }
}