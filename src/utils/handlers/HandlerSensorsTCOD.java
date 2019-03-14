package utils.handlers;

import com.google.gson.JsonObject;
import controllers.tcodtask.get.interfaises.iHandlerSensors;
import main.Manifest;
import utils.files.FileWriter;
import utils.files.iWriter;
import utils.time.Timer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class HandlerSensorsTCOD implements iHandlerSensors
{
    private static final String TEMPERATURE_PARAM = "temperature";
    private static final String HUMIDITY_PARAM = "humidity";
    private static final String LIGHT_PARAM = "light";

    private HttpServletRequest request;
    private iWriter iWriter;

    private HandlerSensorsTCOD(HttpServletRequest request, utils.files.iWriter iWriter)
    {
        this.request = request;
        this.iWriter = iWriter;
    }

    @Override
    public void handleParams()
    {
        JsonObject jsonObject = new JsonObject();

        try {
            jsonObject.addProperty(TEMPERATURE_PARAM,getTemperature(request));
        } catch (Exception e) {
            System.err.println(Timer.getTimeNowForLogs() + " :: [!] Error of parsing temperature!");
        }
        try {
            jsonObject.addProperty(HUMIDITY_PARAM,getHumidity(request));
        } catch (Exception e) {
            System.err.println(Timer.getTimeNowForLogs() + " :: [!] Error of parsing humidity!");
        }
        try {
            jsonObject.addProperty(LIGHT_PARAM,getLight(request));
        } catch (Exception e) {
            System.err.println(Timer.getTimeNowForLogs() + " :: [!] Error of parsing light!");
        }

        iWriter.write(jsonObject.toString());
    }

    private int getTemperature(HttpServletRequest request)
    {
        return Integer.parseInt(request.getParameter(TEMPERATURE_PARAM));
    }

    private int getHumidity(HttpServletRequest request)
    {
        return Integer.parseInt(request.getParameter(HUMIDITY_PARAM));
    }

    private int getLight(HttpServletRequest request)
    {

        return Integer.parseInt(request.getParameter(LIGHT_PARAM));
    }

    public static HandlerSensorsTCOD build(ServletContext servletContext, HttpServletRequest request)
    {
        return new HandlerSensorsTCOD(
                request,
                new FileWriter(servletContext.getRealPath(Manifest.FILE_SENSORS)));
    }
}
