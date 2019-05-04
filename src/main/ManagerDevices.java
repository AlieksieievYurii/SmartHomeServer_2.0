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
import action.DeviceUtils;
import exceptions.DeviceException;
import exceptions.TypeRequestException;
import org.xml.sax.SAXException;
import request.post.Response;
import request.post.interfaises.iResponse;
import service.Service;
import sensors.HandlerSensors;
import utils.hash.HashCode;
import utils.password.PasswordUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


@WebServlet(name = "ManagerDevices")
public class ManagerDevices extends HttpServlet {

    private Service service;

    @Override
    public void init() throws ServletException {
        super.init();
        if (!Manifest.isParsed) try {
            Manifest.parseDefaultConfiguration(getServletContext());
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        if(Manifest.START_WORKER)
        {
            service = new Service(getServletContext());
            service.startService();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        if(service != null)
            service.cancel();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        if (PasswordUtils.isApiPasswordCorrect(request)) {
            try {
                start(request, response);
            } catch (TypeRequestException e) {
                e.printStackTrace();
                wrongRequest(response);
            }
        } else {
            final iResponse resp = new Response(response);
            resp.responseWRONG("wrong password");
        }
    }

    private void actions(HttpServletRequest request, HttpServletResponse response) {
        final ControllerGETActions controllerGETActions =
                Factory.buildControllerGETActions(getServletContext(), request, response);
        try {
            controllerGETActions.execute(DeviceUtils.whatDevice(request));
        } catch (DeviceException e) {
            e.printStackTrace();
            final iResponse resp = new Response(response);
            resp.responseWRONG("wrong device");
        }
    }

    private void hashCode(HttpServletRequest request, HttpServletResponse response) {
        HashCode.sendHashCodeActions(getServletContext(), response);
        HandlerSensors.build(getServletContext(), request).handleSensors();
    }

    private void wrongRequest(HttpServletResponse response) {
        ErrorLogs.errorOfTypeRequest();
        final iResponse resp = new Response(response);
        resp.responseWRONG("wrong request");
    }

    private void start(HttpServletRequest request, HttpServletResponse response) throws TypeRequestException {
        switch (RequestTypeUtils.whatTypeRequest(request)) {
            case GET_ACTIONS:
                actions(request, response);
                break;
            case GET_HASH_CODE_ACTIONS:
                hashCode(request, response);
                break;
        }
    }
}
