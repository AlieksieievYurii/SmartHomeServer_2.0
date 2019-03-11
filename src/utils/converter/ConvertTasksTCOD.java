package utils.converter;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import controllers.tcodtask.iConverter;
import task.PortType;
import task.Task;
import utils.hash.HashCode;

import javax.servlet.ServletContext;
import java.util.List;

public class ConvertTasksTCOD implements iConverter
{
    private static final String EXTRA_HASH_CODE = "hash";
    private static final String EXTRA_TASKS = "tasks";
    private static final String SHORT_EXTRA_TYPE_PORT = "T";
    private static final String SHORT_TYPE_ANALOG= "A";
    private static final String SHORT_TYPE_DIGITAL= "D";
    private static final String SHORT_VALUE= "V";
    private static final String SHORT_PORT_STATUS= "S";
    private static final String SHORT_ID_PORT= "P";


    private ServletContext servletContext;

    public ConvertTasksTCOD(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public String convert(List<Task> tasks) {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();

        for(Task t : tasks)
            jsonArray.add(getJsonObject(t));

        jsonObject.addProperty(EXTRA_HASH_CODE, HashCode.hashCodeTasks(servletContext));
        jsonObject.add(EXTRA_TASKS,jsonArray);

        return jsonObject.toString();
    }

    private JsonObject getJsonObject(Task task)
    {
        JsonObject jsonObject = new JsonObject();

        if(task.getPortType() == PortType.ANALOG) {
            jsonObject.addProperty(SHORT_EXTRA_TYPE_PORT, SHORT_TYPE_ANALOG);
            jsonObject.addProperty(SHORT_VALUE,task.getSignalOnPort());
        }
        else if (task.getPortType() == PortType.DIGITAL) {
            jsonObject.addProperty(SHORT_EXTRA_TYPE_PORT, SHORT_TYPE_DIGITAL);
            jsonObject.addProperty(SHORT_PORT_STATUS,task.getPortStatus().getShortJsonExtra());
        }
        jsonObject.addProperty(SHORT_ID_PORT,task.getPort());

        return jsonObject;
    }
}
