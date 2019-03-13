package request.post;

import action.Action;
import com.google.gson.JsonObject;
import request.post.interfaises.iRequest;
import utils.json.JsonUtils;

import javax.servlet.http.HttpServletRequest;

public class RequestActionTCOD implements iRequest {

    private static final String JSON_ACTION = "data";

    private final HttpServletRequest httpServletRequest;

    public RequestActionTCOD(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    private String readRequest()
    {
        return httpServletRequest.getParameter(JSON_ACTION);
    }

    @Override
    public Action getAction()
    {
        final JsonObject jsonObject = JsonUtils.getJsonObject(readRequest());
        return JsonUtils.toExternalAction(jsonObject);
    }
}
