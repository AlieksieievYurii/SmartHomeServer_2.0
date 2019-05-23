package controllers.ListenerTasks;

import components.action.Action;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import components.pin.FileReaderRegisteredPins;
import request.TypeRequest;
import exceptions.TypeRequestException;
import interfaces.*;
import request.get.Converter;
import request.get.ReaderHashCode;
import request.get.ReaderSensors;
import components.task.Task;
import utils.Response;
import utils.files.FileReaderActions;
import utils.files.FileReaderTasks;
import utils.files.tools.iReaderTasks;
import utils.JsonUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ControllerGetting {
    private iFileReaderActions readerActionsTasks;
    private iConverter iConverter;
    private iResponse iResponse;
    private interfaces.iReaderSensors iReaderSensors;
    private interfaces.iHashCodes iHashCodes;
    private iReaderTasks readerTasks;
    private iReaderRegisteredPins iReaderRegisteredPins;

    private ControllerGetting(
            iFileReaderActions readerActionsTasks,
            iConverter iConverter,
            iResponse iResponse,
            iReaderSensors iSendSensors,
            iHashCodes iHashCodes,
            iReaderTasks iReaderTasks,
            iReaderRegisteredPins iReaderRegisteredPins) {

        this.readerActionsTasks = readerActionsTasks;
        this.iConverter = iConverter;
        this.iResponse = iResponse;
        this.iReaderSensors = iSendSensors;
        this.iHashCodes = iHashCodes;
        this.readerTasks = iReaderTasks;
        this.iReaderRegisteredPins = iReaderRegisteredPins;
    }

    public void executeFor(TypeRequest typeRequest) throws TypeRequestException {
        switch (typeRequest) {
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
            case GET_FREE_TASK_ID:
                sendFreeTaskId();
                break;
            case GET_REGISTERED_PINS:
                sendRegisteredPins();
                break;
            default:
                throw new TypeRequestException("Wrong type request for POST");
        }
    }

    private void sendRegisteredPins() {
        final String registeredPins = iReaderRegisteredPins.getRegisteredPinsAsString();
        iResponse.response(registeredPins);
    }

    private void sendFreeTaskId() {
        final List<Task> tasks = readerTasks.getTasks();
        final JsonObject jsonObject = new JsonObject();
        int max = 0;
        for (Task t : tasks)
            if (t.getId() > max)
                max = t.getId();

        jsonObject.addProperty("id", ++max);
        iResponse.response(jsonObject.toString());

    }

    private void sendHashCodeSensors() {
        final JsonObject jsonObject = iHashCodes.getJsonObjectHashCodeSensors();
        iResponse.response(jsonObject.toString());
    }

    private void sendSensors() {
        JsonArray sensors = iReaderSensors.getSensors();
        if (sensors != null)
            iResponse.response(sensors.toString());
    }

    private void sendJsonTasks() {
        final List<Task> tasks = readerTasks.getTasks();
        final JsonArray jsonElements = JsonUtils.tasksToJsonArray(tasks);
        iResponse.response(jsonElements.toString());
    }

    private void sendJsonActions() {
        List<Action> actions = readerActionsTasks.getActions(null);
        String res = iConverter.convert(actions);
        iResponse.response(res);
    }

    private void sendHashCodeActions() {
        final JsonObject jsonObject = iHashCodes.getJsonObjectHashCodeActions();

        if (jsonObject != null)
            iResponse.response(jsonObject.toString());
    }

    private void sendHashCodeTasks() {
        final JsonObject jsonObject = iHashCodes.getJsonObjectHashCodeTasks();

        if (jsonObject != null)
            iResponse.response(jsonObject.toString());
    }

    public static ControllerGetting build(ServletContext servletContext,
                                          HttpServletResponse httpServletResponse)
    {
        final FileReaderActions fileReader = new FileReaderActions(servletContext);
        final Converter converter = new Converter(servletContext);
        final Response response = new Response(httpServletResponse);
        final ReaderHashCode hashCode = new ReaderHashCode(servletContext);
        final ReaderSensors senderSensors = new ReaderSensors(servletContext);
        final FileReaderTasks fileReaderTasks = new FileReaderTasks(servletContext);
        final FileReaderRegisteredPins fileReaderRegisteredPins = new FileReaderRegisteredPins(servletContext);

        return new ControllerGetting(
                fileReader,
                converter,
                response,
                senderSensors,
                hashCode,
                fileReaderTasks,
                fileReaderRegisteredPins);
    }
}
