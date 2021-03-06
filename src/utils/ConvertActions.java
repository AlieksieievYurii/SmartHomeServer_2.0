package utils;

import components.action.Action;
import components.action.TypePin;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import interfaces.iConverter;

import javax.servlet.ServletContext;
import java.util.List;

public class ConvertActions implements iConverter
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

    public ConvertActions(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public String convert(List<Action> actions) {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();

        for(Action t : actions)
            jsonArray.add(getJsonObject(t));

        jsonObject.addProperty(EXTRA_HASH_CODE, HashCode.getHashCodeActions(servletContext));
        jsonObject.add(EXTRA_TASKS,jsonArray);

        return jsonObject.toString();
    }

    private JsonObject getJsonObject(Action action)
    {
        JsonObject jsonObject = new JsonObject();

        if(action.getTypePin() == TypePin.ANALOG) {
            jsonObject.addProperty(SHORT_EXTRA_TYPE_PORT, SHORT_TYPE_ANALOG);
            jsonObject.addProperty(SHORT_VALUE, action.getSignalOnPort());
        }
        else if (action.getTypePin() == TypePin.DIGITAL) {
            jsonObject.addProperty(SHORT_EXTRA_TYPE_PORT, SHORT_TYPE_DIGITAL);
            jsonObject.addProperty(SHORT_PORT_STATUS, action.getPortStatus().getShortJsonExtra());
        }
        jsonObject.addProperty(SHORT_ID_PORT, action.getPin());

        return jsonObject;
    }
}
