package controllers.request;

import javax.servlet.http.HttpServletRequest;

public class RequestTypeUtils
{
    private static final String TYPE_REQUEST_PARAM = "type";
    public static TypeRequest whatTypeRequest(HttpServletRequest request)
    {
        String typeRequest = request.getParameter(TYPE_REQUEST_PARAM);
        return TypeRequest.whatTypeRequest(typeRequest);
    }
}
