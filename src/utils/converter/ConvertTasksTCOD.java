package utils.converter;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import controllers.tcodtask.iConverter;
import task.PortType;
import task.Task;

import java.util.List;

public class ConvertTasksTCOD implements iConverter
{
    @Override
    public String convert(List<Task> tasks) {
        JsonArray jsonArray = new JsonArray();

        for(Task t : tasks)
            jsonArray.add(getJsonObject(t));

        return jsonArray.toString();
    }

    private JsonObject getJsonObject(Task task)
    {
        JsonObject jsonObject = new JsonObject();

        PortType portType = task.getPortType();
        int port = task.getPort();
        int statusPort = task.getPortStatus();

        if(portType == PortType.ANALOG) {
            jsonObject.addProperty("T", "A");
            jsonObject.addProperty("V",statusPort);
        }
        else if (portType == PortType.DIGITAL) {
            jsonObject.addProperty("T", "D");
            jsonObject.addProperty("S",statusPort>=1?"H":"L");
        }
        jsonObject.addProperty("P",port);

        return jsonObject;
    }
}
