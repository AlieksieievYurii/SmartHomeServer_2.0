package request.get;

import controllers.tcodtask.get.Response;
import utils.files.ActionsTasksReader;


import javax.servlet.ServletContext;

import javax.servlet.http.HttpServletResponse;

public class FactoryControllerGetting
{
    public static ControllerGetting build(ServletContext servletContext,
                                   HttpServletResponse httpServletResponse)
    {
        final ActionsTasksReader fileReader = new ActionsTasksReader(servletContext);
        final Converter converter = new Converter(servletContext);
        final Response response = new Response(httpServletResponse);
        final ReaderHashCode hashCode = new ReaderHashCode(servletContext);
        final ReaderSensors senderSensors = new ReaderSensors(servletContext);

       return new ControllerGetting(
               fileReader,
               converter,
               response,
               senderSensors,
               hashCode);
    }
}
