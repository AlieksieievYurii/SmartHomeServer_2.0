package utils.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
            short statusPort = jsonObject.get(TaskExtra.STATUS_PORT.getJsonExtra()).getAsShort();

            tasks.add(new Task(portType,port,statusPort));
        }

        return tasks;
    }

    public static List<Task> convertTaskToList(String tasks)
    {
        JsonArray jsonElements = convertTasksToJson(tasks);

        return convertTasksToList(jsonElements);
    }
}
