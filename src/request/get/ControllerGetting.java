package request.get;

import action.Action;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import controllers.request.TypeRequest;
import controllers.tcodtask.get.interfaises.iConverter;
import controllers.tcodtask.get.interfaises.iFileReaderActions;
import controllers.tcodtask.get.interfaises.iResponse;
import task.Task;
import utils.files.tools.iReaderTasks;
import utils.json.JsonUtils;
import utils.password.PasswordUtils;

import java.util.List;

public class ControllerGetting
{
    private iFileReaderActions readerActionsTasks;
    private iConverter iConverter;
    private iResponse iResponse;
    private iReaderSensors iReaderSensors;
    private iHashCodes iHashCodes;
    private iReaderTasks readerTasks;

    ControllerGetting(
            iFileReaderActions readerActionsTasks,
            iConverter iConverter,
            iResponse iResponse,
            iReaderSensors iSendSensors,
            iHashCodes iHashCodes,
            iReaderTasks iReaderTasks) {

        this.readerActionsTasks = readerActionsTasks;
        this.iConverter = iConverter;
        this.iResponse = iResponse;
        this.iReaderSensors = iSendSensors;
        this.iHashCodes = iHashCodes;
        this.readerTasks = iReaderTasks;
    }

    public void executeFor(TypeRequest typeRequest)
    {
        switch (typeRequest)
        {
            case GET_HASH_CODE_ACTIONS:
                sendHashCodeActions();
                break;
            case GET_HASH_CODE_TASKS:
                sendHashCodeTasks();
                break;
            case GET_TASKS:
                sendJsonTasks();
                break;
            case GET_ACTIONS:
                sendJsonActions();
                break;
            case GET_SENSORS:
                sendSensors();
                break;
            case GET_HASH_CODE_SENSORS:
                sendHashCodeSensors();
            case GET_FREE_ID:
                sendFreeTaskId();
                break;
        }
    }

    private void sendFreeTaskId() {
        final List<Task> tasks = readerTasks.getTasks();
        final JsonObject jsonObject = new JsonObject();
        int max = 0;
        for(Task t : tasks)
            if(t.getId() > max)
                max = t.getId();

        jsonObject.addProperty("id",++max);
        iResponse.response(jsonObject.toString());

    }

    private void sendHashCodeSensors() {
        final JsonObject jsonObject = iHashCodes.getJsonObjectHashCodeSensors();
        iResponse.response(jsonObject.toString());
    }

    private void sendSensors() {
        JsonArray sensors = iReaderSensors.getSensors();
        if(sensors != null)
            iResponse.response(sensors.toString());
    }

    private void sendJsonTasks()
    {
        final List<Task> tasks = readerTasks.getTasks();
        final JsonArray jsonElements = JsonUtils.tasksToJsonArray(tasks);
        iResponse.response(jsonElements.toString());
    }

    private void sendJsonActions()
    {
        List<Action> actions = readerActionsTasks.getActions(null);
        String res = iConverter.convert(actions);
        iResponse.response(res);
    }

    private void sendHashCodeActions()
    {
        final JsonObject jsonObject = iHashCodes.getJsonObjectHashCodeActions();

        if(jsonObject != null)
            iResponse.response(jsonObject.toString());
    }

    private void sendHashCodeTasks()
    {
        final JsonObject jsonObject = iHashCodes.getJsonObjectHashCodeTasks();

        if(jsonObject != null)
            iResponse.response(jsonObject.toString());
    }
}
