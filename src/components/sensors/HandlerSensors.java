package components.sensors;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import interfaces.iHandlerSensors;
import main.Manifest;
import utils.files.tools.FileWriter;
import utils.files.tools.iWriteFile;
import utils.time.Timer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import static components.sensors.Humidity.HUMIDITY_PARAM;
import static components.sensors.Light.LIGHT_PARAM;
import static components.sensors.Temperature.TEMPERATURE_PARAM;

public class HandlerSensors implements iHandlerSensors
{
    private HttpServletRequest request;
    private iWriteFile iWriter;

    private HandlerSensors(HttpServletRequest request, iWriteFile iWriter)
    {
        this.request = request;
        this.iWriter = iWriter;
    }

    @Override
    public void handleSensors()
    {
        JsonArray jsonElements = new JsonArray();

        try {
            jsonElements.add(getTemperature(request));
        } catch (Exception e) {
            System.err.println(Timer.getTimeNowForLogs() + " :: [!] Error of parsing temperature!");
        }
        try {
            jsonElements.add(getHumidity(request));
        } catch (Exception e) {
            System.err.println(Timer.getTimeNowForLogs() + " :: [!] Error of parsing humidity!");
        }
        try {
            jsonElements.add(getLight(request));
        } catch (Exception e) {
            System.err.println(Timer.getTimeNowForLogs() + " :: [!] Error of parsing light!");
        }

        iWriter.write(jsonElements.toString());
    }

    private JsonObject getTemperature(HttpServletRequest request)
    {
        return new Temperature(Integer.parseInt(request.getParameter(TEMPERATURE_PARAM))).toJsonObject();
    }

    private JsonObject getHumidity(HttpServletRequest request)
    {
        return new Humidity(Integer.parseInt(request.getParameter(HUMIDITY_PARAM))).toJsonObject();
    }

    private JsonObject getLight(HttpServletRequest request)
    {

        return new Light(Integer.parseInt(request.getParameter(LIGHT_PARAM))).toJsonObject();
    }

    public static HandlerSensors build(ServletContext servletContext, HttpServletRequest request)
    {
        return new HandlerSensors(
                request,
                new FileWriter(servletContext.getRealPath(Manifest.FILE_SENSORS)));
    }
}
