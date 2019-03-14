package controllers.tcodtask.get;
import action.Action;
import controllers.tcodtask.get.interfaises.iConverter;
import controllers.tcodtask.get.interfaises.iHandlerSensors;
import controllers.tcodtask.get.interfaises.iReadActionsTasks;
import controllers.tcodtask.get.interfaises.iResponse;
import device.Device;

import java.util.List;

public class ControllerGETActions
{
    private final iReadActionsTasks iReadActions;
    private final controllers.tcodtask.get.interfaises.iConverter iConverter;
    private final iHandlerSensors iHandlerSensors;
    private final iResponse iResponse;

    ControllerGETActions(
            iReadActionsTasks iReadActions,
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
}
