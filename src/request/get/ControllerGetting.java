package request.get;

import action.Action;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
    private iReaderSensors iReaderSensors;
    private iHashCodes iHashCodes;

    ControllerGetting(
            iReadActionsTasks readerActionsTasks,
            iConverter iConverter,
            iResponse iResponse,
            iReaderSensors iSendSensors,
            iHashCodes iHashCodes) {

        this.readerActionsTasks = readerActionsTasks;
        this.iConverter = iConverter;
        this.iResponse = iResponse;
        this.iReaderSensors = iSendSensors;
        this.iHashCodes = iHashCodes;
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
                break;
        }
    }

    private void sendHashCodeSensors() {
        final JsonObject jsonObject = iHashCodes.getJsonObjectHashCodeSensors();

        iResponse.response(jsonObject.toString());
    }

    private void sendSensors()
    {
        JsonArray sensors = iReaderSensors.getSensors();
        if(sensors != null)
            iResponse.response(sensors.toString());
    }

    private void sendJsonTasks() {

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

    }
}
