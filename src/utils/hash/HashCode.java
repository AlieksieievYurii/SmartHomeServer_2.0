package utils.hash;

import main.Manifest;
import utils.files.FileReader;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HashCode
{
    public static void sendHashCodeTasks(ServletContext servletContext, HttpServletResponse response)
    {
        try {
            PrintWriter p = response.getWriter();
            p.print("HASH_CODE: #"+hashCodeTasks(servletContext));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int hash(String s)
    {
        int h = 0;
        int len = s.length();
        for (int i = 0; i < len; i++)
            h = 31 * h + s.charAt(i);
        return h;
    }

    public static int hashCodeTasks(ServletContext servletContext)
    {
        final FileReader fileReader =
                new FileReader(servletContext.getRealPath(Manifest.FILE_TASKS_TCOD));
        String s = fileReader.readFile();

        return hash(s);
    }
}
