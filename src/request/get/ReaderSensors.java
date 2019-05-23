package request.get;

import com.google.gson.JsonArray;
import interfaces.iReaderSensors;
import components.sensors.SensorUtils;
import javax.servlet.ServletContext;

public class ReaderSensors implements iReaderSensors {

    private ServletContext servletContext;

    public ReaderSensors(ServletContext servletContext) {
        this.servletContext = servletContext;

    }

    @Override
    public JsonArray getSensors() {
        return SensorUtils.readSensors(servletContext);
    }
}
