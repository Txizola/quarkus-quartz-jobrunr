package dataModels;

public class SendResponse {
    private String orderId;
    private Schedule schedule;
    private HTTPTask task;

    public SendResponse() {
    }

    public SendResponse(String orderId, Schedule schedule, HTTPTask task) {
        this.orderId = orderId;
        this.schedule = schedule;
        this.task = task;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public HTTPTask getTask() {
        return task;
    }

    public void setTask(HTTPTask task) {
        this.task = task;
    }
}
