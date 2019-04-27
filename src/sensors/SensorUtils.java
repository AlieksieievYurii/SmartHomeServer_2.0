package sensors;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import main.Manifest;
import utils.files.tools.FileReader;
import javax.servlet.ServletContext;
import java.io.IOException;


public class SensorUtils {

    public static JsonArray readSensors(ServletContext servletContext) {
        FileReader fileReader =
                new FileReader(servletContext.getRealPath(Manifest.FILE_SENSORS));
        try {
            return new JsonParser().parse(fileReader.readFile()).getAsJsonArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
