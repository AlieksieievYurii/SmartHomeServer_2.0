package request.post;

import request.post.interfaises.FileWorker;
import request.post.interfaises.iRequest;
import request.post.interfaises.iResponse;
import request.post.interfaises.iWriteAction;
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
        final iResponse iResponse = new Response(response);

        return new ControllerPOSTAction(iRequest,iWriteAction,iResponse);
    }
}
