package controllers.DeviceManager;
import components.action.Action;
import interfaces.iConverter;
import interfaces.iHandlerSensors;
import interfaces.iFileReaderActions;
import interfaces.iResponse;
import components.action.Device;
import components.sensors.HandlerSensors;
import utils.Response;
import utils.ConvertActions;
import utils.files.FileReaderActions;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ControllerGETActions
{
    private final iFileReaderActions iReadActions;
    private final interfaces.iConverter iConverter;
    private final iHandlerSensors iHandlerSensors;
    private final iResponse iResponse;

    private ControllerGETActions(
            iFileReaderActions iReadActions,
            iConverter iConverter,
            iHandlerSensors iHandlerTCOD,
            iResponse iResponse) {

        this.iReadActions = iReadActions;
        this.iConverter = iConverter;
        this.iHandlerSensors = iHandlerTCOD;
        this.iResponse = iResponse;
    }

    public void execute(Device forDevice)
    {
        final List<Action> actions = iReadActions.getActions(forDevice);
        final String tasksForTCOD = iConverter.convert(actions);
        iHandlerSensors.handleSensors();
        iResponse.response(tasksForTCOD);
    }

    public static ControllerGETActions build(ServletContext servletContext,
                                             HttpServletRequest request,
                                             HttpServletResponse response)
    {
        final FileReaderActions fileReader = new FileReaderActions(
                servletContext);
        final ConvertActions convertTasksTCOD = new ConvertActions(servletContext);
        final Response resp = new Response(response);

        return new ControllerGETActions(
                fileReader,
                convertTasksTCOD,
                HandlerSensors.build(servletContext,request),
                resp);
    }
}
