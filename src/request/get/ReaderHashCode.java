package request.get;
import com.google.gson.JsonObject;
import interfaces.iHashCodes;
import utils.HashCode;

import javax.servlet.ServletContext;

public class ReaderHashCode implements iHashCodes {

    private ServletContext servletContext;

    public ReaderHashCode(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public JsonObject getJsonObjectHashCodeActions() {
        return HashCode.getJsonOfHashCodeActions(servletContext);
    }

    @Override
    public JsonObject getJsonObjectHashCodeTasks() {
        return HashCode.getJSonHashCodeTasks(servletContext);
    }

    @Override
    public JsonObject getJsonObjectHashCodeSensors()
    {
        return HashCode.getJsonOfHashCodeSensors(servletContext);
    }
}
