package utils;

import components.action.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import components.action.TypePort;
import exceptions.*;
import components.pin.RegisteredPin;
import components.task.Task;
import components.task.TimerJob;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    public static JsonArray getJsonArray(String s) {
        return new JsonParser().parse(s).getAsJsonArray();
    }

    public static JsonObject getJsonObject(String json) throws IllegalStateException {
        return new JsonParser().parse(json).getAsJsonObject();
    }

    private static List<Action> getListActions(JsonArray jsonArray) throws ActionException {
        List<Action> actions = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); i++) {
            try {
                final JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();

                Device forDevice = Device.whatDevice(
                        jsonObject.get(
                                ActionExtra
                                        .FOR_DEVICE
                                        .getJsonExtra())
                                .getAsString());


                final TypePort typePort = TypePort.getPortType(
                        jsonObject.get(ActionExtra.TYPE_PORT.getJsonExtra()).getAsString());


                short port = jsonObject.get(ActionExtra.PORT.getJsonExtra()).getAsShort();

                if (typePort == TypePort.ANALOG) {

                    actions.add(new Action(
                            forDevice,
                            typePort,
                            port,
                            jsonObject.get(
                                    ActionExtra.SIGNAL_ON_PORT
                                            .getJsonExtra())
                                    .getAsInt()));

                } else if (typePort == TypePort.DIGITAL) {

                    actions.add(new Action(
                            forDevice,
                            typePort,
                            port,
                            PortStatus.getByName(
                                    jsonObject.get(
                                            ActionExtra.STATUS_PORT
                                                    .getJsonExtra())
                                            .getAsString())));
                }
            } catch (PortStatusException | SignalException | DeviceException | PortTypeException e) {
                throw new ActionException(e.getMessage());
            }
        }

        return actions;
    }

    public static List<Action> toListActions(String tasks) throws ActionException {
        JsonArray jsonElements = getJsonArray(tasks);

        return getListActions(jsonElements);
    }

    public static List<Action> toListApiActions(JsonArray jsonArray) throws ActionException {
        List<Action> list = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); i++)
            list.add(toApiAction(jsonArray.get(i).getAsJsonObject()));
        return list;
    }

    public static Action toApiAction(JsonObject jsonObject) throws ActionException {
        final Device forDevice;

        try {
            forDevice = Device.whatDevice(
                    jsonObject.get(
                            ApiActionExtras
                                    .FOR_DEVICE
                                    .getJsonExtra())
                            .getAsString());
        } catch (DeviceException e) {
            throw new ActionException(e.getMessage());
        }


        final TypePort typePort;
        try {
            typePort = TypePort.getPortType(
                    jsonObject.get(
                            ApiActionExtras.PORT_TYPE.getJsonExtra()
                    ).getAsString());
        } catch (PortTypeException e) {
            throw new ActionException(e.getMessage());
        }

        final int port = jsonObject.get(ApiActionExtras.PORT_ID.getJsonExtra()).getAsInt();


        if (typePort == TypePort.ANALOG) {
            try {
                return new Action(
                        forDevice,
                        TypePort.ANALOG,
                        port,
                        jsonObject.get(
                                ApiActionExtras
                                        .PORT_VALUE
                                        .getJsonExtra())
                                .getAsInt());
            } catch (SignalException e) {
                throw new ActionException(e.getMessage());
            }
        } else if (typePort == TypePort.DIGITAL) {
            try {
                return new Action(
                        forDevice,
                        TypePort.DIGITAL,
                        port,
                        PortStatus.getByName(
                                jsonObject.get(
                                        ApiActionExtras
                                                .PORT_STATUS
                                                .getJsonExtra())
                                        .getAsString()));
            } catch (PortStatusException e) {
                throw new ActionException(e.getMessage());
            }
        } else
            return null;
    }

    public static JsonArray toJsonArray(List<Action> actions) {
        JsonArray jsonElements = new JsonArray();

        for (Action a : actions)
            jsonElements.add(a.toJsonObject());

        return jsonElements;
    }

    public static JsonArray toJsonArrayApi(List<Action> actions) {
        JsonArray jsonElements = new JsonArray();

        for (Action a : actions)
            jsonElements.add(Action.getApiActionAsJsonObject(a));

        return jsonElements;
    }

    public static List<Action> selectForDevice(Device device, List<Action> actions) {
        List<Action> selectedActions = new ArrayList<>();

        for (Action a : actions)
            if (a.getDevice() == device)
                selectedActions.add(a);

        return selectedActions;
    }

    public static List<Task> getListTasks(JsonArray jsonArray) throws TaskException {
        List<Task> tasks = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); i++)
            tasks.add(Task.getTaskByJson(jsonArray.get(i).getAsJsonObject()));

        return tasks;
    }

    public static JsonArray tasksToJsonArray(List<Task> tasks) {
        final JsonArray jsonElements = new JsonArray();

        for (Task t : tasks)
            jsonElements.add(Task.toJsonObject(t));

        return jsonElements;
    }

    public static JsonArray RegisteredPinsToJsonArray(List<RegisteredPin> registeredPins)
    {
        final JsonArray jsonElements = new JsonArray();

        for(RegisteredPin r : registeredPins)
            jsonElements.add(r.toJson());
        return jsonElements;
    }

    public static JsonObject getJobJson(Task task) {
        switch (task.getTypeTask()) {
            case Timer:
                TimerJob timerJob = (TimerJob) task.getTask();
                return TimerJob.getTimerJobJsonObject(timerJob);

            default:
                return null;
        }
    }
}
