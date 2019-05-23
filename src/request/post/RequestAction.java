package request.post;

import components.action.Action;
import com.google.gson.JsonObject;
import exceptions.*;
import interfaces.iRequest;
import utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;

public class RequestAction implements iRequest {

    private static final String JSON_ACTION = "data";

    private final HttpServletRequest httpServletRequest;

    public RequestAction(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    private String readRequest() throws NullPointerException
    {
        return httpServletRequest.getParameter(JSON_ACTION);
    }

    @Override
    public Action getAction() throws ActionException {
        try {
            final JsonObject jsonObject = JsonUtils.getJsonObject(readRequest());
            return JsonUtils.toApiAction(jsonObject);
        }catch (IllegalStateException  e)
        {
            throw new ActionException(e.getMessage());
        }catch ( NullPointerException e)
        {
            throw new ActionException("data is none");
        }
    }
}
