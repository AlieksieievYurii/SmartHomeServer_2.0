package components.pin;

import components.action.Device;
import components.action.TypePin;
import com.google.gson.JsonObject;
import exceptions.DeviceException;
import exceptions.PortException;
import exceptions.PortTypeException;

public class RegisteredPin
{
    private static final String API_PIN = "pin";
    private static final String API_TYPE_PORT = "type_port";
    private static final String API_DEVICE = "device";
    private static final String API_NAME = "name";
    private static final String API_DESCRIPTION = "description";

    private int pin;
    private TypePin typePin;
    private Device device;
    private String name;
    private String description;

    private RegisteredPin(int pin, TypePin typePin, Device device, String name, String description) {
        this.pin = pin;
        this.typePin = typePin;
        this.device = device;
        this.name = name;
        this.description = description;
    }

    public int getPin() {
        return pin;
    }

    public TypePin getTypePin() {
        return typePin;
    }

    public Device getDevice() {
        return device;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean equals(RegisteredPin registeredPin) {
        if(registeredPin == null)
            return false;

        return registeredPin.pin == this.pin && registeredPin.device == this.device;
    }

    public JsonObject toJson()
    {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(API_PIN,pin);
        jsonObject.addProperty(API_TYPE_PORT, typePin.toString());
        jsonObject.addProperty(API_DEVICE,device.toString());
        jsonObject.addProperty(API_NAME,name);
        jsonObject.addProperty(API_DESCRIPTION,description);

        return jsonObject;
    }

    public static RegisteredPin parse(JsonObject jsonObject) throws
            PortException,
            PortTypeException,
            DeviceException {

        /*
        {
            "pin":12,
            "type_port":"analog",
            "device":"TCOD",
            "name":"test_one",
            "description":"jist test!"
        }
         */

        final int pin = jsonObject.get(API_PIN).getAsInt();
        if(pin < 0)
            throw new PortException(pin);

        final TypePin typePin = TypePin.getPortType(jsonObject.get(API_TYPE_PORT).getAsString());
        final Device device = Device.whatDevice(jsonObject.get(API_DEVICE).getAsString());
        final String name = jsonObject.get(API_NAME).getAsString();
        final String description = jsonObject.get(API_DESCRIPTION).getAsString();

        return new RegisteredPin(pin, typePin,device,name,description);
    }
}
