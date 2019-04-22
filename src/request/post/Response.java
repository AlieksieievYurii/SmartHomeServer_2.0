package request.post;

import com.google.gson.JsonObject;
import request.post.interfaises.iResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Response implements iResponse {

    private HttpServletResponse response;

    public Response(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public void responseOk() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Response","OK");
        print(jsonObject.toString());
    }

    @Override
    public void responseWRONG(String description) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Response","WRONG");
        jsonObject.addProperty("description",description);
        print(jsonObject.toString());
    }

    private void print(String s)
    {
        try {
            PrintWriter printWriter =  response.getWriter();
            printWriter.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
