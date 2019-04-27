package main;

/*
    [*] ManagerDevices - This is mechanism which controls HardWare part of smart Home

    Example of URL:
    ../SmartHomeServer_2_0_war_exploded/managerTasks?p=123456789&type=action&device=tcod&temperature=14&humidity=23&light=12
    where:
    p - password
    type - request type(hash - for getting hash code of tasks, action - for getting json file of tasks)
    device - type of device: only TCOD at the moment
    temperature/humidity/light - value of sensors from TCOD
 */

import controllers.tcodtask.get.ControllerGETActions;
import controllers.tcodtask.get.Factory;
import controllers.request.RequestTypeUtils;
import controllers.errors.ErrorLogs;
import controllers.errors.ResponseExceptions;
import device.DeviceUtils;
import exceptions.DeviceException;
import service.Service;
import sensors.HandlerSensors;
import utils.hash.HashCode;
import utils.password.PasswordUtils;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ManagerDevices")
public class ManagerDevices extends HttpServlet {

    public ManagerDevices() {
        new Service().startService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        if (PasswordUtils.passwordIsCorrect(request))
            start(request, response);
        else
            ResponseExceptions.wrongPassword(response);
    }

    private void actions(HttpServletRequest request, HttpServletResponse response) {
        final ControllerGETActions controllerGETActions =
                Factory.buildControllerGETActions(getServletContext(), request, response);
        try {
            controllerGETActions.execute(DeviceUtils.whatDevice(request));
        } catch (DeviceException e) {
            e.printStackTrace();
        }
    }

    private void hashCode(HttpServletRequest request, HttpServletResponse response) {
        HashCode.sendHashCodeActions(getServletContext(), response);
        HandlerSensors.build(getServletContext(), request).handleSensors();
    }

    private void wrongRequest(HttpServletResponse response) {
        ErrorLogs.errorOfTypeRequest();
        ResponseExceptions.wrongTypeRequest(response);
    }

    private void start(HttpServletRequest request, HttpServletResponse response) {
        switch (RequestTypeUtils.whatTypeRequest(request)) {
            case GET_ACTIONS:
                actions(request, response);
                break;
            case GET_HASH_CODE_ACTIONS:
                hashCode(request, response);
                break;
            default:
                wrongRequest(response);
                break;
        }
    }
}
