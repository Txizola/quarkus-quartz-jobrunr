package dataModels;

public interface Schedule{
    void schedule(HTTPTask httpTask, Delay delay) throws Exception;
}
