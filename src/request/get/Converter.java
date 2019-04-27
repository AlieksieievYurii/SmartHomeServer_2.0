package request.get;

import action.Action;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import controllers.tcodtask.get.interfaises.iConverter;
import action.ApiActionExtras;
import utils.hash.HashCode;

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
                ApiActionExtras.FOR_DEVICE.getJsonExtra(),
                action.getDevice().toString());

        jsonObject.addProperty(ApiActionExtras.PORT_TYPE.getJsonExtra(),
                action.getPortType().toString());

        jsonObject.addProperty(ApiActionExtras.PORT_ID.getJsonExtra(),
                action.getPort());

        switch (action.getPortType())
        {
            case ANALOG:
                jsonObject.addProperty(ApiActionExtras.PORT_VALUE.getJsonExtra(),
                        action.getSignalOnPort());
                break;
            case DIGITAL:
                jsonObject.addProperty(ApiActionExtras.PORT_STATUS.getJsonExtra(),
                        action.getPortStatus().getJsonExtra());
                break;
        }

        return jsonObject;
    }
}
