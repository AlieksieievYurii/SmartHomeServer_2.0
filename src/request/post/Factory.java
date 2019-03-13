package request.post;

import request.post.interfaises.FileWorker;
import request.post.interfaises.iRequest;
import request.post.interfaises.iResponse;
import request.post.interfaises.iWriteAction;
import main.Manifest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Factory
{
    public static POSTControllerTCODAction build(ServletContext context, HttpServletRequest request, HttpServletResponse response)
    {
        final iRequest iRequest = new RequestActionTCOD(request);
        final FileWorker fileWorker = new FileEmployer(context.getRealPath(Manifest.FILE_TASKS_TCOD));
        final iWriteAction iWriteAction = new ActionRecorder(fileWorker);
        final iResponse iResponse = new Response(response);

        return new POSTControllerTCODAction(iRequest,iWriteAction,iResponse);
    }
}
