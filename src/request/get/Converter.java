package request.get;

import components.action.Action;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import interfaces.iConverter;
import components.action.ActionAPI;
import utils.HashCode;

import javax.servlet.ServletContext;
import java.util.List;

public class Converter implements iConverter {

    private ServletContext context;

    public Converter(ServletContext context) {
        this.context = context;
    }

    @Override
    public String convert(List<Action> actions)
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(HashCode.EXTRA_HASH_CODE,HashCode.getHashCodeActions(context));
        jsonObject.add("actions",getJsonArrayActions(actions));
        return jsonObject.toString();
    }

    private JsonArray getJsonArrayActions(List<Action> actions)
    {
        final JsonArray jsonElements = new JsonArray();

        for(Action a : actions)
            jsonElements.add(getJsonObjectAction(a));

        return jsonElements;
    }

    private JsonObject getJsonObjectAction(Action action)
    {
        final JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty(
                ActionAPI.FOR_DEVICE.getJsonExtra(),
                action.getDevice().toString());

        jsonObject.addProperty(ActionAPI.PIN_TYPE.getJsonExtra(),
                action.getTypePin().toString());

        jsonObject.addProperty(ActionAPI.PIN_ID.getJsonExtra(),
                action.getPin());

        switch (action.getTypePin())
        {
            case ANALOG:
                jsonObject.addProperty(ActionAPI.PIN_VALUE.getJsonExtra(),
                        action.getSignalOnPort());
                break;
            case DIGITAL:
                jsonObject.addProperty(ActionAPI.PIN_STATUS.getJsonExtra(),
                        action.getPortStatus().getJsonExtra());
                break;
        }

        return jsonObject;
    }
}
