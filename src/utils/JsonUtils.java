package utils;

import components.action.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import components.action.TypePin;
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
                                ActionAPI
                                        .FOR_DEVICE
                                        .getJsonExtra())
                                .getAsString());


                final TypePin typePin = TypePin.getPortType(
                        jsonObject.get(ActionAPI.PIN_TYPE.getJsonExtra()).getAsString());


                short port = jsonObject.get(ActionAPI.PIN_ID.getJsonExtra()).getAsShort();

                if (typePin == TypePin.ANALOG) {

                    actions.add(new Action(
                            forDevice,
                            typePin,
                            port,
                            jsonObject.get(
                                    ActionAPI.PIN_VALUE
                                            .getJsonExtra())
                                    .getAsInt()));

                } else if (typePin == TypePin.DIGITAL) {

                    actions.add(new Action(
                            forDevice,
                            typePin,
                            port,
                            PortStatus.getByName(
                                    jsonObject.get(
                                            ActionAPI.PIN_STATUS
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
                            ActionAPI
                                    .FOR_DEVICE
                                    .getJsonExtra())
                            .getAsString());
        } catch (DeviceException e) {
            throw new ActionException(e.getMessage());
        }


        final TypePin typePin;
        try {
            typePin = TypePin.getPortType(
                    jsonObject.get(
                            ActionAPI.PIN_TYPE.getJsonExtra()
                    ).getAsString());
        } catch (PortTypeException e) {
            throw new ActionException(e.getMessage());
        }

        final int port = jsonObject.get(ActionAPI.PIN_ID.getJsonExtra()).getAsInt();


        if (typePin == TypePin.ANALOG) {
            try {
                return new Action(
                        forDevice,
                        TypePin.ANALOG,
                        port,
                        jsonObject.get(
                                ActionAPI
                                        .PIN_VALUE
                                        .getJsonExtra())
                                .getAsInt());
            } catch (SignalException e) {
                throw new ActionException(e.getMessage());
            }
        } else if (typePin == TypePin.DIGITAL) {
            try {
                return new Action(
                        forDevice,
                        TypePin.DIGITAL,
                        port,
                        PortStatus.getByName(
                                jsonObject.get(
                                        ActionAPI
                                                .PIN_STATUS
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
