package controllers.tcodtask.get;

import utils.converter.ConvertActions;
import utils.files.ActionsTasksReader;
import utils.handlers.HandlerSensorsTCOD;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Factory
{
    public static ControllerGETActions buildControllerGETActions(ServletContext servletContext,
                                                                 HttpServletRequest request,
                                                                 HttpServletResponse response)
    {
        final ActionsTasksReader fileReader = new ActionsTasksReader(
                servletContext);
        final ConvertActions convertTasksTCOD = new ConvertActions(servletContext);
        final Response responseTCOD = new Response(response);

        return new ControllerGETActions(
                fileReader,
                convertTasksTCOD,
                HandlerSensorsTCOD.build(servletContext,request),
                responseTCOD);
    }
}
