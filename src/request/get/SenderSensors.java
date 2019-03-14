package request.get;

import com.google.gson.JsonArray;
import sensors.SensorUtils;
import javax.servlet.ServletContext;

public class SenderSensors implements iReaderSensors {

    private ServletContext servletContext;

    public SenderSensors(ServletContext servletContext) {
        this.servletContext = servletContext;

    }

    @Override
    public JsonArray getSensors() {
        return SensorUtils.readSensors(servletContext);
    }
}
