package request.post;

import action.Action;
import com.google.gson.JsonObject;
import exceptions.*;
import request.post.interfaises.iRequest;
import utils.json.JsonUtils;

import javax.servlet.http.HttpServletRequest;

public class RequestAction implements iRequest {

    private static final String JSON_ACTION = "data";

    private final HttpServletRequest httpServletRequest;

    public RequestAction(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    private String readRequest()
    {
        return httpServletRequest.getParameter(JSON_ACTION);
    }

    @Override
    public Action getAction() throws ActionException {
        final JsonObject jsonObject = JsonUtils.getJsonObject(readRequest());

        return JsonUtils.toApiAction(jsonObject);
    }
}
