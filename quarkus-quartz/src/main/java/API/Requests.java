package API;

import Scheduler.QuartzScheduler;
import dataModels.Order;
import dataModels.RequestServices;
import dataModels.SendResponse;
import org.quartz.SchedulerException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.util.Map;

@Path("/schedule")
public class Requests {
    private RequestServices requestServices = RequestServices.getInstance();
    private QuartzScheduler quartzScheduler = QuartzScheduler.getInstance();

    //testing
    /*@GET
    public void schedulerTest() throws SchedulerException {
        new QuartzScheduler();
    }*/
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SendResponse schedule(Map<String,Object> orderReceived) throws ParseException, SchedulerException {
        //transform the json received into the object Order
        Order orderInMemory = requestServices.setOrder(orderReceived);
        //set the trigger start scheduler
        quartzScheduler.setTrigger(orderInMemory.getSchedule().getDelay().getStartTime(), orderInMemory.getSchedule().getDelay().getDelay());
        quartzScheduler.ActivateScheduler();
        //add in memory
        requestServices.addOrder(orderInMemory);
        return requestServices.getResponse(orderInMemory);
    }
}
