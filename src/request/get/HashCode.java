package request.get;
import com.google.gson.JsonObject;

import javax.servlet.ServletContext;

public class HashCode implements iHashCodes {

    private ServletContext servletContext;

    public HashCode(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public JsonObject getJsonObjectHashCodeActions() {
        return utils.hash.HashCode.getJsonOfHashCodeActions(servletContext);
    }

    @Override
    public JsonObject getJsonObjectHashCodeTasks() {
        return null;
    }
}
