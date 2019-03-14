package utils.hash;

import com.google.gson.JsonObject;
import utils.files.ActionsTasksReader;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HashCode {
    public static final String EXTRA_HASH_CODE = "hashCode";
    public static final String EXTRA_SENSORS = "sensors";

    public static void sendHashCodeActions(ServletContext servletContext, HttpServletResponse response) {
        try {
            PrintWriter p = response.getWriter();
            p.print("EXTRA_HASH_CODE: #" + getHashCodeActions(servletContext));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JsonObject getJsonOfHashCodeActions(ServletContext servletContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(EXTRA_HASH_CODE, getHashCodeActions(servletContext));
        return jsonObject;
    }

    private static long hash(String s) {
        int h = 0;
        int len = s.length();
        for (int i = 0; i < len; i++)
            h = 31 * h + s.charAt(i);
        return h;
    }

    public static long getHashCodeActions(ServletContext servletContext) {
        final ActionsTasksReader fileReader =
                new ActionsTasksReader(servletContext);
        String s = fileReader.getActionsAsString();

        return hash(s);
    }
}
