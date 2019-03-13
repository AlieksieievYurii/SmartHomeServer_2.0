package controllers.tcodtask.get;
import controllers.tcodtask.get.interfaises.iResponseTCOD;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseTCOD implements iResponseTCOD {

    private HttpServletResponse httpServletResponse;

    public ResponseTCOD(HttpServletResponse httpServletResponse) {
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
