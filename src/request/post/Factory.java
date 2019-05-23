package request.post;

import interfaces.FileWorker;
import interfaces.iRequest;
import interfaces.iResponseStatus;
import interfaces.iWriteAction;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Factory
{
    public static ControllerPOSTAction build(ServletContext context, HttpServletRequest request, HttpServletResponse response)
    {
        final iRequest iRequest = new RequestAction(request);
        final FileWorker fileWorker = new FileEmployer(context);
        final iWriteAction iWriteAction = new ActionRecorder(fileWorker);
        final iResponseStatus iResponseStatus = new ResponseStatus(response);

        return new ControllerPOSTAction(iRequest,iWriteAction, iResponseStatus);
    }
}
