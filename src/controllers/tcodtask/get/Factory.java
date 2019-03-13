package controllers.tcodtask.get;

import main.Manifest;
import utils.converter.ConvertTasksTCOD;
import utils.files.FileReader;
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
        final FileReader fileReader = new FileReader(
                servletContext.getRealPath(Manifest.FILE_ACTIONS));
        final ConvertTasksTCOD convertTasksTCOD = new ConvertTasksTCOD(servletContext);
        final ResponseTCOD responseTCOD = new ResponseTCOD(response);

        return new ControllerGETActions(
                fileReader,
                convertTasksTCOD,
                HandlerSensorsTCOD.build(servletContext,request),
                responseTCOD);
    }
}
