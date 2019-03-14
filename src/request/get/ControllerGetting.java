package request.get;

import action.Action;
import controllers.request.TypeRequest;
import controllers.tcodtask.get.interfaises.iConverter;
import controllers.tcodtask.get.interfaises.iReadActionsTasks;
import controllers.tcodtask.get.interfaises.iResponse;

import java.util.List;

public class ControllerGetting
{
    private iReadActionsTasks readerActionsTasks;
    private iConverter iConverter;
    private iResponse iResponse;
    private iHashCodes iHashCodes;

    public ControllerGetting(
            iReadActionsTasks readerActionsTasks,
            iConverter iConverter,
            iResponse iResponse,
            iHashCodes iHashCodes) {

        this.readerActionsTasks = readerActionsTasks;
        this.iConverter = iConverter;
        this.iResponse = iResponse;
        this.iHashCodes = iHashCodes;
    }

    public void executeFor(TypeRequest typeRequest)
    {
        switch (typeRequest)
        {
            case HASH_CODE_ACTIONS:
                iHashCodes.sendHashCodeActions();
                break;
            case HASH_CODE_TASKS:
                iHashCodes.sendHashCodeTasks();
                break;
            case TASKS:
                sendJsonTasks();
                break;
            case ACTIONS:
                sendJsonActions();
                break;
        }
    }

    private void sendJsonTasks() {

    }

    private void sendJsonActions()
    {
        List<Action> actions = readerActionsTasks.getActions(null);
        String res = iConverter.convert(actions);
        iResponse.response(res);
    }
}
