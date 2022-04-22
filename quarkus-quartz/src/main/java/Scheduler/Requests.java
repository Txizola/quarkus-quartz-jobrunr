package Scheduler;

import dataModels.RequestBody;
import dataModels.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/schedule")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Requests {

    @POST
    public Response schedule(RequestBody responseRequest) {
        return responseRequest.returnResponse();
    }
}
