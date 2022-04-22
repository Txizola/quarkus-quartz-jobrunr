package dataModels;

public class RequestBody {
    private String id;
    private Delay delay;
    private HTTPTask httpTask;

    public Response returnResponse() {
        Response response = new Response();
        response.getTask().setType(this.httpTask.getType());
        response.getSchedule().setType(this.delay.getType());
        response.setOrderId(this.id);
        return response;
    }
}
