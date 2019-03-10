package controllers.tcodtask;

import controllers.tcodtask.responcer.ResponseTCOD;
import utils.converter.ConvertTasksTCOD;
import utils.files.FileReader;
import utils.handlers.HandlerSensorsTCOD;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Factory
{
    public static ControllerTCODTask buildControllerTCODTask(ServletContext servletContext,
                                                             HttpServletRequest request,
                                                             HttpServletResponse response)
    {
        final FileReader fileReader = new FileReader(
                servletContext.getRealPath("/WEB-INF/res/Tasks.json"));
        final ConvertTasksTCOD convertTasksTCOD = new ConvertTasksTCOD(servletContext);
        final ResponseTCOD responseTCOD = new ResponseTCOD(response);

        return new ControllerTCODTask(
                fileReader,
                convertTasksTCOD,
                HandlerSensorsTCOD.build(servletContext,request),
                responseTCOD);
    }
}
