package utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import components.sensors.SensorUtils;
import utils.files.FileReaderActions;
import utils.files.FileReaderTasks;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HashCode {
    public static final String EXTRA_HASH_CODE = "hashCode";

    public static void sendHashCodeActions(ServletContext servletContext, HttpServletResponse response) {
        try {
            PrintWriter p = response.getWriter();
            p.print("EXTRA_HASH_CODE: #" + getHashCodeActions(servletContext));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JsonObject getJsonOfHashCodeSensors(ServletContext servletContext)
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(EXTRA_HASH_CODE, getHashCodeSensors(servletContext));
        return jsonObject;
    }

    public static JsonObject getJsonOfHashCodeActions(ServletContext servletContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(EXTRA_HASH_CODE, getHashCodeActions(servletContext));
        return jsonObject;
    }

    public static JsonObject getJSonHashCodeTasks(ServletContext context)
    {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(EXTRA_HASH_CODE,getHashCodeTasks(context));
        return jsonObject;
    }

    private static long hash(String s) {
        int h = 0;
        int len = s.length();
        for (int i = 0; i < len; i++)
            h = 31 * h + s.charAt(i);
        return h;
    }

    private static long getHashCodeSensors(ServletContext servletContext)
    {
        final JsonArray jsonElements = SensorUtils.readSensors(servletContext);
        if(jsonElements != null)
            return hash(jsonElements.toString());
        else
            return 0;
    }

    public static long getHashCodeActions(ServletContext servletContext) {
        final FileReaderActions fileReader =
                new FileReaderActions(servletContext);
        String s = fileReader.getActionsAsString();

        return hash(s);
    }

    private static long getHashCodeTasks(ServletContext servletContext)
    {
        final FileReaderTasks fileReaderTasks = new FileReaderTasks(servletContext);
        String s = fileReaderTasks.getAsString();

        return hash(s);
    }

}
