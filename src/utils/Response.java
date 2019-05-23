package utils;

import interfaces.iResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Response implements iResponse {

    private HttpServletResponse httpServletResponse;

    public Response(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    @Override
    public void response(String responseAPI) {
        try {
            final PrintWriter printWriter = httpServletResponse.getWriter();
            printWriter.print(responseAPI);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
