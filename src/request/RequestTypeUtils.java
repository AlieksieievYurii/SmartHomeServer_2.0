package request;

import exceptions.TypeRequestException;

import javax.servlet.http.HttpServletRequest;

public class RequestTypeUtils {
    private static final String TYPE_REQUEST_PARAM = "type";

    public static TypeRequest whatTypeRequest(HttpServletRequest request) throws TypeRequestException {
        String typeRequest = request.getParameter(TYPE_REQUEST_PARAM);
        if (typeRequest == null)
            throw new TypeRequestException("typeRequest is null");
        else
            return TypeRequest.whatTypeRequest(typeRequest);
    }
}
