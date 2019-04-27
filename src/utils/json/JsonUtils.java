package utils.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import action.PortStatus;
import action.PortType;
import action.Action;
import action.ActionExtra;
import action.Device;
import action.ApiActionExtras;
import exceptions.*;
import task.Task;
import task.TimerJob;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static JsonArray getJsonArray(String tasks) {
        return new JsonParser().parse(tasks).getAsJsonArray();
    }

    public static JsonObject getJsonObject(String json) {
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


                final PortType portType = PortType.getPortType(
                        jsonObject.get(ActionExtra.TYPE_PORT.getJsonExtra()).getAsString());


                short port = jsonObject.get(ActionExtra.PORT.getJsonExtra()).getAsShort();

                if (portType == PortType.ANALOG) {

                    actions.add(new Action(
                            forDevice,
                            portType,
                            port,
                            jsonObject.get(
                                    ActionExtra.SIGNAL_ON_PORT
                                            .getJsonExtra())
                                    .getAsInt()));

                } else if (portType == PortType.DIGITAL) {

                    actions.add(new Action(
                            forDevice,
                            portType,
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


        final PortType portType;
        try {
            portType = PortType.getPortType(
                    jsonObject.get(
                            ApiActionExtras.PORT_TYPE.getJsonExtra()
                    ).getAsString());
        } catch (PortTypeException e) {
            throw new ActionException(e.getMessage());
        }

        final int port = jsonObject.get(ApiActionExtras.PORT_ID.getJsonExtra()).getAsInt();


        if (portType == PortType.ANALOG) {
            try {
                return new Action(
                        forDevice,
                        PortType.ANALOG,
                        port,
                        jsonObject.get(
                                ApiActionExtras
                                        .PORT_VALUE
                                        .getJsonExtra())
                                .getAsInt());
            } catch (SignalException e) {
                throw new ActionException(e.getMessage());
            }
        } else if (portType == PortType.DIGITAL) {
            try {
                return new Action(
                        forDevice,
                        PortType.DIGITAL,
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
