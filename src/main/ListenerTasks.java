package main;

import controllers.request.RequestTypeUtils;
import controllers.errors.ErrorLogs;
import controllers.errors.ResponseExceptions;
import request.get.ControllerGetting;
import request.get.FactoryControllerGetting;
import request.post.Factory;
import request.post.ControllerPOSTAction;
import request.post.task.ControllerPOSTTask;
import utils.password.PasswordUtils;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ListenerTasks")
public class ListenerTasks extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        if (PasswordUtils.passwordIsCorrect(request))
            whatPost(request, response);
        else
            ResponseExceptions.wrongPassword(response);
    }

    private void whatPost(HttpServletRequest request, HttpServletResponse response) {
        switch (RequestTypeUtils.whatTypeRequest(request)) {
            case POST_TASK:
                postTask(request, response);
                break;
            case POST_ACTION:
                postAction(request, response);
                break;
            default:
                wrongRequest(response);
                break;
        }
    }

    private void wrongRequest(HttpServletResponse response) {
        ErrorLogs.errorOfTypeRequest();
        ResponseExceptions.wrongTypeRequest(response);
    }

    private void postTask(HttpServletRequest request, HttpServletResponse response) {
        final ControllerPOSTTask controllerPOSTTask = ControllerPOSTTask.build(getServletContext(),response);
        controllerPOSTTask.execute(request);
    }

    private void postAction(HttpServletRequest request, HttpServletResponse response) {
        final ControllerPOSTAction postControllerTCODAction =
                Factory.build(
                        getServletContext(),
                        request,
                        response);

        postControllerTCODAction.execute();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        final ControllerGetting controllerGetting = FactoryControllerGetting.build(getServletContext(), response);
        controllerGetting.executeFor(RequestTypeUtils.whatTypeRequest(request));
    }
}
