package dataModels;

public class Order {
    private String id;
    private Schedule schedule;
    private HTTPTask task;

    public Order(){}

    public Order(String id, Schedule schedule, HTTPTask task) {
        this.id = id;
        this.schedule = schedule;
        this.task = task;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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