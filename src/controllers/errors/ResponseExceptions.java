package controllers.errors;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseExceptions
{
    public static void wrongPassword(HttpServletResponse response)
    {
        try {
            final PrintWriter printWriter = response.getWriter();
            printWriter.print(" CODE_RESPONSE: 12\n Message: Wrong password!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void wrongDeviceType(HttpServletResponse response)
    {
        try {
            final PrintWriter printWriter = response.getWriter();
            printWriter.print(" CODE_RESPONSE: 16\n Message: Wrong device type!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void wrongTypeRequest(HttpServletResponse response)
    {
        try {
            final PrintWriter printWriter = response.getWriter();
            printWriter.print("CODE_RESPONSE: 22\n Message: Wrong request type!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
