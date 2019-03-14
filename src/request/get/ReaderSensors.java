package request.get;

import com.google.gson.JsonObject;
import utils.files.iReadSensors;

import javax.servlet.ServletContext;


public class ReaderSensors implements iReadSensors {

    private ServletContext servletContext;

    public ReaderSensors(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public JsonObject readSensors() {
        return null;
    }
}
