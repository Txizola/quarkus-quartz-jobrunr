package api;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dataModels.Order;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

@Path("/schedule")
public class Requests {

    private Set<Order> orders = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    @GET
    public Set<Order> list() {
            return orders;
        }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Set<Order> add(Order order){
        orders.add(order);
        System.out.println(order.getId());
        System.out.println(order.getDelay().getDelay());
        System.out.println(order.getDelay().getStartTime());
        System.out.println(order.getDelay().getType());
        return orders;
    }
}