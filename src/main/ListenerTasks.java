package main;

import controllers.request.RequestTypeUtils;
import controllers.request.TypeRequest;
import controllers.errors.ErrorLogs;
import controllers.errors.ResponseExceptions;
import request.post.Factory;
import request.post.ControllerPOSTAction;
import utils.password.PasswordUtils;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ListenerTasks")
public class ListenerTasks extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        System.err.println("POST::");

        if (PasswordUtils.passwordIsCorrect(request))
            postTaskOrAction(request, response);
        else
            ResponseExceptions.wrongPassword(response);
    }

    private void postTaskOrAction(HttpServletRequest request, HttpServletResponse response) {
        TypeRequest i = RequestTypeUtils.whatTypeRequest(request);

        if (i == TypeRequest.TASK) {
            //TODO Implements Worker and Tasks(Plan for Service)
        } else if (i == TypeRequest.ACTION) {
            runAction(request, response);
        } else {
            ErrorLogs.errorOfTypeRequest();
            ResponseExceptions.wrongTypeRequest(response);
        }
    }

    private void runAction(HttpServletRequest request, HttpServletResponse response) {
        final ControllerPOSTAction postControllerTCODAction =
                Factory.build(
                        getServletContext(),
                        request,
                        response);

        postControllerTCODAction.execute();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        switch (RequestTypeUtils.whatTypeRequest(request))
        {
            case HASH_CODE_ACTIONS:
                //TODO Implement hash code of file Actions.txt
                break;
            case HASH_CODE_TASKS:
                //TODO Implement hash code of file Tasks.txt(But it's not realized)
                break;
            case TASKS:
                //TODO Implement getting json file of tasks(But it's not realized)
                break;
            case ACTIONS:
                //TODO Implement getting json file of actions
                break;
        }
    }
}
