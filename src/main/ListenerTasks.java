package main;

import controllers.request.RequestTypeUtils;
import controllers.request.TypeRequest;
import controllers.errors.ErrorLogs;
import controllers.errors.ResponseExceptions;
import request.post.Factory;
import request.post.POSTControllerTCODAction;
import device.Device;
import device.DeviceUtils;
import utils.password.PasswordUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static controllers.errors.ResponseExceptions.wrongDeviceType;

@WebServlet(name = "ListenerTasks")
public class ListenerTasks extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        if (DeviceUtils.whatDevice(request) == Device.TCOD)
            actionForTCOD(request, response);
        else
            wrongDeviceType(response);
    }

    private void actionForTCOD(HttpServletRequest request, HttpServletResponse response)
    {
        final POSTControllerTCODAction postControllerTCODAction =
                Factory.build(
                        getServletContext(),
                        request,
                        response);

        postControllerTCODAction.execute();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO Implements getting HashCode and getting all tasks
    }
}
