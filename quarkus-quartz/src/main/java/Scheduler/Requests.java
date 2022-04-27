package Scheduler;

import dataModels.Order;
import dataModels.RequestServices;
import dataModels.SendResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@Path("/schedule")
public class Requests {
    private RequestServices requestServices = RequestServices.getInstance();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SendResponse schedule(Map<String,Object> orderReceived){
        Order orderInMemory = requestServices.setOrder(orderReceived);
        requestServices.addOrder(orderInMemory);
        return requestServices.getResponse(orderInMemory);
    }
}
