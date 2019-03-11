package utils.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import task.PortStatus;
import task.PortType;
import task.Task;
import task.TaskExtra;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils
{
    public static JsonArray convertTasksToJson(String tasks)
    {
        return new JsonParser().parse(tasks).getAsJsonArray();
    }

    public static List<Task> convertTasksToList(JsonArray jsonArray)
    {
        List<Task> tasks = new ArrayList<>();

        for(int i = 0; i < jsonArray.size(); i++)
        {
            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();

            PortType portType = PortType.getPortType(
                    jsonObject.get(TaskExtra.TYPE_PORT.getJsonExtra()).getAsString()
            );

            short port = jsonObject.get(TaskExtra.PORT.getJsonExtra()).getAsShort();

            if(portType == PortType.ANALOG)
                tasks.add(new Task(
                        portType,
                        port,
                        jsonObject.get(
                                TaskExtra.SIGNAL_ON_PORT
                                        .getJsonExtra())
                                        .getAsInt()));

            else if(portType == PortType.DIGITAL)
                tasks.add(new Task(
                        portType,
                        port,
                        PortStatus.stringToEnum(
                        jsonObject.get(
                                TaskExtra.STATUS_PORT
                                        .getJsonExtra())
                                .getAsString())));
        }

        return tasks;
    }

    public static List<Task> convertTaskToList(String tasks)
    {
        JsonArray jsonElements = convertTasksToJson(tasks);

        return convertTasksToList(jsonElements);
    }
}
