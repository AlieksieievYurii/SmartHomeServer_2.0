package main;

import controllers.tcodtask.ControllerTCODTask;
import controllers.tcodtask.Factory;
import device.Device;
import device.DeviceUtils;
import utils.password.PasswordUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controllers.tcodtask.responcer.errors.ResponseExceptions.wrongDeviceType;
import static controllers.tcodtask.responcer.errors.ResponseExceptions.wrongPassword;

@WebServlet(name = "ManagerDevices")
public class ManagerDevices extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        //nothing
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        if(PasswordUtils.passwordIsCorrect(request))
            run(request,response);
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

    private void taskForTCOD(HttpServletRequest request,HttpServletResponse response)
    {
        final ControllerTCODTask controllerTCODTask = Factory.buildControllerTCODTask(this,request,response);
        controllerTCODTask.execute();
    }
}
