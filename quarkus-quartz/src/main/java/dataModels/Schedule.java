package dataModels;

public class Schedule {
    private String type;
    private Delay delay;

    public Schedule(){}

    public Schedule(String type, Delay delay) {
        this.type = type;
        this.delay = delay;
    }

    public Delay getDelay() {
        return delay;
    }

    public void setDelay(Delay delay) {
        this.delay = delay;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
