package main;

/*
    [*] ManagerDevices - This is mechanism which controls HardWare part of smart Home

    Example of URL:
    ../SmartHomeServer_2_0_war_exploded/managerTasks?p=123456789&type=task&device=tcod&temperature=14&humidity=23&light=12
    where:
    p - password
    type - request type(hash - for getting hash code of tasks, task - for getting json file of tasks)
    device - type of device: only TCOD at the moment
    temperature/humidity/light - value of sensors from TCOD
 */

import controllers.tcodtask.ControllerTCODTask;
import controllers.tcodtask.Factory;
import controllers.tcodtask.request.RequestTypeUtils;
import controllers.tcodtask.request.TypeRequest;
import controllers.tcodtask.responcer.errors.ErrorLogs;
import controllers.tcodtask.responcer.errors.ResponseExceptions;
import device.Device;
import device.DeviceUtils;
import service.Service;
import utils.handlers.HandlerSensorsTCOD;
import utils.hash.HashCode;
import utils.password.PasswordUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controllers.tcodtask.responcer.errors.ResponseExceptions.wrongDeviceType;
import static controllers.tcodtask.responcer.errors.ResponseExceptions.wrongPassword;

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
        if (DeviceUtils.whatDevice(request) == Device.TCOD)
            taskForTCOD(request, response);
         else
            wrongDeviceType(response);
    }

    private void hashCodeOrTasks(HttpServletRequest request,HttpServletResponse response)
    {
        controllers.tcodtask.request.TypeRequest i = RequestTypeUtils.whatTypeRequest(request);
        if (i == TypeRequest.TASKS) {
            run(request, response);
        } else if (i == TypeRequest.HASH_CODE)
        {
            HashCode.sendHashCodeTasks(getServletContext(), response);
            HandlerSensorsTCOD.build(getServletContext(),request).handleParams();
        } else {
            ErrorLogs.errorOfTypeRequest();
            ResponseExceptions.wrongTypeRequest(response);
        }
    }

    private void taskForTCOD(HttpServletRequest request,HttpServletResponse response)
    {
        final ControllerTCODTask controllerTCODTask =
                Factory.buildControllerTCODTask(getServletContext(),request,response);
        controllerTCODTask.execute();
    }
}
