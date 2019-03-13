package controllers.tcodtask.get;
import action.Action;
import controllers.tcodtask.get.interfaises.iConverter;
import controllers.tcodtask.get.interfaises.iHandlerTCOD;
import controllers.tcodtask.get.interfaises.iReadTasks;
import controllers.tcodtask.get.interfaises.iResponseTCOD;

import java.util.List;

public class GETControllerTCODTask
{
    private final controllers.tcodtask.get.interfaises.iReadTasks iReadTasks;
    private final controllers.tcodtask.get.interfaises.iConverter iConverter;
    private final controllers.tcodtask.get.interfaises.iHandlerTCOD iHandlerTCOD;
    private final controllers.tcodtask.get.interfaises.iResponseTCOD iResponseTCOD;

    GETControllerTCODTask(
            iReadTasks iReadTasks,
            iConverter iConverter,
            iHandlerTCOD iHandlerTCOD,
            iResponseTCOD iResponseTCOD) {

        this.iReadTasks = iReadTasks;
        this.iConverter = iConverter;
        this.iHandlerTCOD = iHandlerTCOD;
        this.iResponseTCOD = iResponseTCOD;
    }

    public void execute()
    {
        final List<Action> actions = iReadTasks.getActions();
        final String tasksForTCOD = iConverter.convert(actions);
        iHandlerTCOD.handleParams();
        iResponseTCOD.response(tasksForTCOD);
    }
}
