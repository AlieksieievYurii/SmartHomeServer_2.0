package request.get;
import com.google.gson.JsonObject;
import utils.hash.HashCode;

import javax.servlet.ServletContext;

public class ReaderHashCode implements iHashCodes {

    private ServletContext servletContext;

    ReaderHashCode(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public JsonObject getJsonObjectHashCodeActions() {
        return HashCode.getJsonOfHashCodeActions(servletContext);
    }

    @Override
    public JsonObject getJsonObjectHashCodeTasks() {
        return null;
    }

    @Override
    public JsonObject getJsonObjectHashCodeSensors()
    {
        return HashCode.getJsonOfHashCodeSensors(servletContext);
    }
}
