package dataModels;

import Scheduler.QuartzScheduler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class RequestServices {

    private List<Order> orderList = new ArrayList<>();
    private static RequestServices requestInstance = new RequestServices();
    public static RequestServices getInstance() {
        return requestInstance;
    }

    public Order setOrder(Map<String,Object> orderReceived) throws ParseException {
        Order order = new Order();
        Schedule schedule = new Schedule();
        Delay delay = new Delay();
        HTTPTask httpTask = new HTTPTask();

        order.setId((String)orderReceived.get("id"));

        Map<String, String> scheduleReceived = (Map<String, String>) orderReceived.get("schedule");
        delay.setType(((String)scheduleReceived.get("type")));
        //fix later (cant get int value)
        Date dateHelper =new SimpleDateFormat("dd/MM/yyyy").parse(String.valueOf(scheduleReceived.get("startTime")));
        delay.setStartTime(dateHelper);
        int intHelper = Integer.valueOf(String.valueOf(scheduleReceived.get("delay")));
        delay.setDelay(intHelper);

        Map<String,String> taskReceived = (Map<String, String>) orderReceived.get("task");
        httpTask.setType((String)taskReceived.get("type"));
        httpTask.setUrl((String)taskReceived.get("url"));
        httpTask.setMethod((String)taskReceived.get("method"));

        order.setSchedule(schedule);
        order.getSchedule().setDelay(delay);
        order.setTask(httpTask);

        return order;
    }
    public String addOrder(Order orderReceived){
        orderList.add(orderReceived);

        return orderReceived.getId();
    }
    public SendResponse getResponse(Order orderReceived){
        SendResponse sendResponse = new SendResponse();
        Schedule responseSchedule = new Schedule();
        HTTPTask responseHttpTask = new HTTPTask();

        sendResponse.setSchedule(responseSchedule);
        sendResponse.setTask(responseHttpTask);

        sendResponse.setOrderId(orderReceived.getId());
        sendResponse.getSchedule().setType(orderReceived.getTask().getType());
        sendResponse.getTask().setType(orderReceived.getTask().getType());
        return sendResponse;
    }
}
