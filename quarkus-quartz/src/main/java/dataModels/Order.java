package dataModels;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.beans.ConstructorProperties;

@JsonIgnoreProperties
public class Order {

    @JsonProperty
    private final String id;
    @JsonProperty
    private final Delay schedule;
    @JsonProperty
    private final HTTPTask task;

    @ConstructorProperties({"id","schedule","task"})
    public Order(String id, Delay schedule, HTTPTask task) {
        this.id = id;
        this.schedule = schedule;
        this.task = task;
    }

    @JsonGetter
    public String getId() {
        return id;
    }
    @JsonGetter
    public Delay getDelay() {
        return schedule;
    }
    @JsonGetter
    public HTTPTask getTask() {
        return task;
    }
}