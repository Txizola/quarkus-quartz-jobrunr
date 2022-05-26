package dataModels;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.beans.ConstructorProperties;

@JsonIgnoreProperties
public class Delay{

    @JsonProperty("type")
    public final String type;

    @JsonProperty("startTime")
    public final Long startTime;

    @JsonProperty("delay")
    public final Integer delay;

    @ConstructorProperties({"type","startTime","delay"})
    public Delay(String type, Long startTime, Integer delay) {
        this.type = type;
        this.startTime = startTime;
        this.delay = delay;
    }

    @JsonGetter
    public String getType() {
        return type;
    }
    @JsonGetter
    public Long getStartTime() {
        return startTime;
    }
    @JsonGetter
    public Integer getDelay() {
        return delay;
    }
}
