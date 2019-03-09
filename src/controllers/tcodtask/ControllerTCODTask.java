package controllers.tcodtask;
import task.Task;

import java.util.List;

public class ControllerTCODTask
{
    private iReadTasks iReadTasks;
    private iConverter iConverter;
    private iHandlerTCOD iHandlerTCOD;
    private iResponseTCOD iResponseTCOD;

    public ControllerTCODTask(
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
        final List<Task> tasks = iReadTasks.read();
        final String tasksForTCOD = iConverter.convert(tasks);
        iHandlerTCOD.handleParams();
        iResponseTCOD.response(tasksForTCOD);
    }
}
