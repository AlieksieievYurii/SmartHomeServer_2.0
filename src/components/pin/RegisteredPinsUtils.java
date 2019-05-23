package components.pin;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import exceptions.DeviceException;
import exceptions.PortException;
import exceptions.PortTypeException;
import main.Manifest;
import utils.files.tools.FileReader;
import utils.files.tools.FileWriter;
import utils.JsonUtils;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegisteredPinsUtils {
    private ServletContext context;

    public RegisteredPinsUtils(ServletContext context) {
        this.context = context;
    }

    public void registerPin(HttpServletRequest request) throws Exception {
        final RegisteredPin registeredPin = getPin(request);
        final List<RegisteredPin> registeredPins = getRegisteredPinsFromFile();
        if(isExist(registeredPins,registeredPin))
            throw new RegisteredPinException("Pin is already existed!");

        registeredPins.add(registeredPin);
        writeToFile(registeredPins);
    }

    public void editRegisteredPin(HttpServletRequest request) throws Exception
    {
        final RegisteredPin registeredPin = getPin(request);
        final List<RegisteredPin> registeredPins = getRegisteredPinsFromFile();
        if(!isExist(registeredPins,registeredPin))
            throw new RegisteredPinException("Not registered pin: " + registeredPin.getPin());

        replaceRegisteredPin(registeredPins,registeredPin);
        writeToFile(registeredPins);
    }

    private void replaceRegisteredPin(List<RegisteredPin> registeredPins, RegisteredPin registeredPin) {

        for(int i = 0; i < registeredPins.size(); i++)
            if(registeredPins.get(i).equals(registeredPin)) {
                registeredPins.remove(i);
                break;
            }

        registeredPins.add(registeredPin);
    }

    private boolean isExist(List<RegisteredPin> pins, RegisteredPin registeredPin) {
        for (RegisteredPin r : pins)
            if (registeredPin.equals(r))
                return true;
        return false;
    }


    private void writeToFile(List<RegisteredPin> registeredPins) {
        final FileWriter fileWriter = new FileWriter(context.getRealPath(Manifest.FILE_REGISTERED_PINS));
        fileWriter.write(JsonUtils.RegisteredPinsToJsonArray(registeredPins).toString());
    }

    private List<RegisteredPin> getRegisteredPinsFromFile() throws IOException, DeviceException, PortException, PortTypeException {
        final FileReader fileReader = new FileReader(context.getRealPath(Manifest.FILE_REGISTERED_PINS));
        final JsonArray jsonArray = JsonUtils.getJsonArray(fileReader.readFile());
        final List<RegisteredPin> registeredPins = new ArrayList<>();

        for (JsonElement jsonObject : jsonArray)
            registeredPins.add(RegisteredPin.parse(jsonObject.getAsJsonObject()));

        return registeredPins;
    }

    private static RegisteredPin getPin(HttpServletRequest request) throws DeviceException, PortException, PortTypeException {
        final JsonObject jsonObject = new JsonParser().parse(request.getParameter("data")).getAsJsonObject();
        return RegisteredPin.parse(jsonObject);
    }


    public static class RegisteredPinException extends Exception {
         RegisteredPinException(String message) {
            super(message);
        }
    }

}
