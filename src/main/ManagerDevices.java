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
import controllers.request.TypeRequest;
import controllers.errors.ErrorLogs;
import controllers.errors.ResponseExceptions;
import device.DeviceUtils;
import service.Service;
import sensors.HandlerSensors;
import utils.hash.HashCode;
import utils.password.PasswordUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controllers.errors.ResponseExceptions.wrongPassword;

@WebServlet(name = "ManagerDevices")
public class ManagerDevices extends HttpServlet
{

    public ManagerDevices() {
        new Service().startService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        //nothing
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        if(PasswordUtils.passwordIsCorrect(request))
            hashCodeOrTasks(request,response);
        else
            wrongPassword(response);
    }

    private void run(HttpServletRequest request,HttpServletResponse response)
    {
        final ControllerGETActions ControllerGETActions =
                Factory.buildControllerGETActions(getServletContext(),request,response);
        ControllerGETActions.execute(DeviceUtils.whatDevice(request));
    }

    private void hashCodeOrTasks(HttpServletRequest request,HttpServletResponse response)
    {
        TypeRequest i = RequestTypeUtils.whatTypeRequest(request);
        if (i == TypeRequest.GET_ACTIONS) {
            run(request, response);
        } else if (i == TypeRequest.GET_HASH_CODE_ACTIONS)
        {
            HashCode.sendHashCodeActions(getServletContext(), response);
            HandlerSensors.build(getServletContext(),request).handleSensors();
        } else {
            ErrorLogs.errorOfTypeRequest();
            ResponseExceptions.wrongTypeRequest(response);
        }
    }
}
