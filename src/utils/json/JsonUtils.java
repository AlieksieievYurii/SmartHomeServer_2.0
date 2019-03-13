package utils.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import action.PortStatus;
import action.PortType;
import action.Action;
import action.ActionExtra;
import request.post.PostExtras;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils
{
    public static JsonArray getJsonArray(String tasks)
    {
        return new JsonParser().parse(tasks).getAsJsonArray();
    }

    public static JsonObject getJsonObject(String json)
    {
        return new JsonParser().parse(json).getAsJsonObject();
    }

    public static List<Action> getListActions(JsonArray jsonArray)
    {
        List<Action> actions = new ArrayList<>();

        for(int i = 0; i < jsonArray.size(); i++)
        {
            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();

            PortType portType = PortType.getPortType(
                    jsonObject.get(ActionExtra.TYPE_PORT.getJsonExtra()).getAsString()
            );

            short port = jsonObject.get(ActionExtra.PORT.getJsonExtra()).getAsShort();

            if(portType == PortType.ANALOG)
                actions.add(new Action(
                        portType,
                        port,
                        jsonObject.get(
                                ActionExtra.SIGNAL_ON_PORT
                                        .getJsonExtra())
                                        .getAsInt()));

            else if(portType == PortType.DIGITAL)
                actions.add(new Action(
                        portType,
                        port,
                        PortStatus.stringToEnum(
                        jsonObject.get(
                                ActionExtra.STATUS_PORT
                                        .getJsonExtra())
                                .getAsString())));
        }

        return actions;
    }

    public static List<Action> toListActions(String tasks)
    {
        JsonArray jsonElements = getJsonArray(tasks);

        return getListActions(jsonElements);
    }

    public static Action toExternalAction(JsonObject jsonObject)
    {
        final PortType portType =
                PortType.getPortType(
                              jsonObject.get(
                                      PostExtras.PORT_TYPE.getJsonExtra()
                              ).getAsString()
                );
        final int port = jsonObject.get(PostExtras.PORT_ID.getJsonExtra()).getAsInt();


        if(portType == PortType.ANALOG)
            return new Action(
                    PortType.ANALOG,
                    port,
                    jsonObject.get(
                            PostExtras
                                    .PORT_VALUE
                                    .getJsonExtra())
                            .getAsInt());
        else if(portType == PortType.DIGITAL)
        {
            return new Action(
                    PortType.DIGITAL,
                    port,
                    PortStatus.stringToEnum(
                            jsonObject.get(
                                    PostExtras
                                            .PORT_STATUS
                                            .getJsonExtra())
                                    .getAsString()));
        }else return null;
    }

    public static JsonArray toJsonArray(List<Action> actions)
    {
        JsonArray jsonElements = new JsonArray();

        for(Action a : actions)
            jsonElements.add(a.toJsonObject());

        return jsonElements;
    }
}
