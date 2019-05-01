package main;

import controllers.request.RequestTypeUtils;
import controllers.errors.ErrorLogs;
import exceptions.TypeRequestException;
import request.get.ControllerGetting;
import request.get.FactoryControllerGetting;
import request.post.Factory;
import request.post.ControllerPOSTAction;
import request.post.Response;
import request.post.interfaises.iResponse;
import request.post.task.ControllerPOSTTask;
import request.post.task.ControllerRemovingTask;
import utils.password.PasswordUtils;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ListenerTasks")
public class ListenerTasks extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        if (PasswordUtils.passwordIsCorrect(request)) {
            try {
                whatPost(request, response);
            } catch (TypeRequestException e) {
                e.printStackTrace();
                wrongRequest(response);
            }
        } else {
            final iResponse resp = new Response(response);
            resp.responseWRONG("wrong password");
        }
    }

    private void whatPost(HttpServletRequest request, HttpServletResponse response) throws TypeRequestException {
        switch (RequestTypeUtils.whatTypeRequest(request)) {
            case POST_TASK:
                postTask(request, response);
                break;
            case POST_ACTION:
                postAction(request, response);
                break;
            case DELETE_REMOVE_TASK:
                removeTask(request,response);
                break;
            default:
                throw new TypeRequestException("Wrong type request for POST");
        }
    }

    private void removeTask(HttpServletRequest request, HttpServletResponse response) {
        final ControllerRemovingTask controllerRemovingTask =
                ControllerRemovingTask.build(getServletContext(), response);
        controllerRemovingTask.execute(request);
    }

    private void wrongRequest(HttpServletResponse response) {
        ErrorLogs.errorOfTypeRequest();
        final iResponse resp = new Response(response);
        resp.responseWRONG("wrong request");
    }

    private void postTask(HttpServletRequest request, HttpServletResponse response) {
        final ControllerPOSTTask controllerPOSTTask = ControllerPOSTTask.build(getServletContext(), response);
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
        try {
            controllerGetting.executeFor(RequestTypeUtils.whatTypeRequest(request));
        } catch (TypeRequestException e) {
            e.printStackTrace();
            wrongRequest(response);
        }
    }
}
