package controllers.tcodtask.get;

import utils.converter.ConvertActions;
import utils.files.FileReaderActions;
import sensors.HandlerSensors;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Factory
{
    public static ControllerGETActions buildControllerGETActions(ServletContext servletContext,
                                                                 HttpServletRequest request,
                                                                 HttpServletResponse response)
    {
        final FileReaderActions fileReader = new FileReaderActions(
                servletContext);
        final ConvertActions convertTasksTCOD = new ConvertActions(servletContext);
        final Response responseTCOD = new Response(response);

        return new ControllerGETActions(
                fileReader,
                convertTasksTCOD,
                HandlerSensors.build(servletContext,request),
                responseTCOD);
    }
}
