package request.get;

import utils.hash.HashCode;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

public class SenderHashCode implements iHashCodes {

    private HttpServletResponse httpServletResponse;
    private ServletContext servletContext;

    public SenderHashCode(HttpServletResponse httpServletResponse,
                          ServletContext servletContext) {
        this.httpServletResponse = httpServletResponse;
        this.servletContext = servletContext;
    }

    @Override
    public void sendHashCodeActions()
    {
        HashCode.sendJsonOfHashCodeActions(servletContext,httpServletResponse);
    }

    @Override
    public void sendHashCodeTasks() {

    }
}
