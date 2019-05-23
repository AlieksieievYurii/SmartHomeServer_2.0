package controllers.ListenerTasks;

import request.TypeRequest;
import interfaces.iResponseStatus;
import components.pin.RegisteredPinsUtils;
import request.post.ControllerPOSTAction;
import request.post.Factory;
import request.post.ResponseStatus;
import request.post.task.ControllerPOSTTask;
import request.post.task.ControllerRemovingTask;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerPosting
{
    private ServletContext context;
    private HttpServletRequest request;
    private HttpServletResponse response;

    private ControllerPosting(ServletContext context,
                             HttpServletRequest request,
                             HttpServletResponse response) {
        this.context = context;
        this.request = request;
        this.response = response;
    }

    public void executeFor(TypeRequest typeRequest)
    {
        switch (typeRequest)
        {
            case POST_ACTION:
                postAction();
                break;
            case POST_TASK:
                postTask();
                break;
            case POST_REGISTERED_PIN:
                postRegisteredPin();
                break;
            case EDIT_REGISTERED_PIN:
                editRegisteredPin();
                break;
            case DELETE_REMOVE_TASK:
                removeTask();
                break;
        }
    }

    private void removeTask()
    {
        final ControllerRemovingTask controllerRemovingTask =
                ControllerRemovingTask.build(context, response);
        controllerRemovingTask.execute(request);
    }

    private void editRegisteredPin() {
        final RegisteredPinsUtils registeredPinsUtils = new RegisteredPinsUtils(context);
        final iResponseStatus resp = new ResponseStatus(response);
        try {
            registeredPinsUtils.editRegisteredPin(request);
        } catch (Exception e) {
            resp.responseWRONG(e.getMessage());
            return;
        }
        resp.responseOk();
    }

    private void postRegisteredPin() {
        final RegisteredPinsUtils registeredPinsUtils = new RegisteredPinsUtils(context);
        final iResponseStatus resp = new ResponseStatus(response);
        try {
            registeredPinsUtils.registerPin(request);
        } catch (Exception e) {
            resp.responseWRONG(e.getMessage());
            return;
        }
        resp.responseOk();
    }

    private void postTask() {
        final ControllerPOSTTask controllerPOSTTask =
                ControllerPOSTTask.build(context, response);
        controllerPOSTTask.execute(request);
    }

    private void postAction() {
        final ControllerPOSTAction postControllerTCODAction =
                Factory.build(
                        context,
                        request,
                        response);

        postControllerTCODAction.execute();
    }

    public static ControllerPosting build(ServletContext context,
                                          HttpServletRequest request,
                                          HttpServletResponse response)
    {
        return new ControllerPosting(context,request,response);
    }
}
