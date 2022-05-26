package dataModels;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.beans.ConstructorProperties;
import java.util.Map;

@JsonIgnoreProperties({"headers"})
public class HTTPTask{

    @JsonProperty("type")
    private final String type;
    @JsonProperty("url")
    private final String url;
    @JsonProperty("method")
    private final String method;
    @JsonProperty("headers")
    private final Map<String, String> headers;
    @JsonProperty("body")
    private final String body;


    @ConstructorProperties({"type","url","method","headers","body"})
    public HTTPTask(String type, String url, String method,Map<String, String> headers, String body) {
        this.type = type;
        this.url = url;
        this.method = method;
        this.headers = headers;
        this.body = body;
    }

    @JsonGetter
    public String getType() {
        return type;
    }
    @JsonGetter
    public String getUrl() {
        return url;
    }
    @JsonGetter
    public String getMethod() {
        return method;
    }
    @JsonGetter
    public Map<String, String> getHeaders(){
        return headers;
    }
    @JsonGetter
    public String getBody() {
        return body;
    }
}