package httprequests;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {

    public void setConnection(String url, String method, String headerKey, String headerValue) throws IOException {
        URL urlApi = new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlApi.openConnection();
        con.setRequestProperty(headerKey, headerValue);
        con.setRequestMethod(method);
    }
}
