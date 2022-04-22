package dataModels;

public class Response {
    private String orderId;
    private Schedule schedule;
    private Task task;

    public Response() {
    }

    public Response(String orderId, Schedule schedule, Task task) {
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

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
