package utils.hash;

import controllers.tcodtask.iReadTasks;
import utils.files.FileReader;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HashCode
{
    public static void hashCodeTasks(ServletContext servletContext, HttpServletResponse response)
    {
        final FileReader fileReader =
                new FileReader(servletContext.getRealPath("/WEB-INF/res/Tasks.json"));
         String s = fileReader.readFile();

        try {
            PrintWriter p = response.getWriter();
            p.print("HASH_CODE:"+hash(s));
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
}
