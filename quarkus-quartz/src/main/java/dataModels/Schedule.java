package dataModels;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.beans.ConstructorProperties;
import java.util.Date;

@JsonIgnoreProperties
public class Schedule{
    public String type;

    public Schedule(String type){

    }
    @JsonGetter
    public String getType() {
        return type;
    }
}
