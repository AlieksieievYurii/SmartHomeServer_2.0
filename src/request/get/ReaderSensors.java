package request.get;

import com.google.gson.JsonArray;
import sensors.SensorUtils;
import javax.servlet.ServletContext;

public class ReaderSensors implements iReaderSensors {

    private ServletContext servletContext;

    ReaderSensors(ServletContext servletContext) {
        this.servletContext = servletContext;

    }

    @Override
    public JsonArray getSensors() {
        return SensorUtils.readSensors(servletContext);
    }
}
