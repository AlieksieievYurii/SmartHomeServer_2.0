package request.post;

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
       print("OK");
    }

    @Override
    public void responseWRONG() {
        print("WRONG");
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
