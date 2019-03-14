package utils.hash;

import com.google.gson.JsonObject;
import main.Manifest;
import utils.files.ActionsTasksReader;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HashCode
{
    public static final String EXTRA_HASH_CODE = "hashCode";

    public static void sendHashCodeActions(ServletContext servletContext, HttpServletResponse response)
    {
        try {
            PrintWriter p = response.getWriter();
            p.print("EXTRA_HASH_CODE: #"+ hashCodeActions(servletContext));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendJsonOfHashCodeActions(ServletContext servletContext, HttpServletResponse response)
    {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(EXTRA_HASH_CODE,hashCodeActions(servletContext));
            PrintWriter p = response.getWriter();
            p.print(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long hash(String s)
    {
        int h = 0;
        int len = s.length();
        for (int i = 0; i < len; i++)
            h = 31 * h + s.charAt(i);
        return h;
    }

    public static long hashCodeActions(ServletContext servletContext)
    {
        final ActionsTasksReader fileReader =
                new ActionsTasksReader(servletContext);
        String s = fileReader.readFile();

        return hash(s);
    }
}
