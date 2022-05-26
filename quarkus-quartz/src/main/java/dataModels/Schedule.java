package dataModels;

public interface Schedule{
    Delay getDelay();

    void setJob();
    void setTrigger();
    void activateScheduler();
}
