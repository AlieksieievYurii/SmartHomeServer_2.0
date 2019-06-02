package components.action;

import com.google.gson.JsonObject;
import exceptions.*;

import java.util.Objects;

public class Action {
    private Device device;
    private TypePin typePin;
    private int pin;
    private int signalOnPort;
    private PortStatus portStatus;

    public Action(Device device, TypePin typePin, int pin, int signalOnPort) throws SignalException {
        this.device = device;
        this.typePin = typePin;
        this.pin = pin;
        if(signalOnPort >= 0 && signalOnPort <= 255)
            this.signalOnPort = signalOnPort;
        else
            throw new SignalException(signalOnPort);
    }

    public Action(Device device, TypePin typePin, int pin, PortStatus portStatus) {
        this.device = device;
        this.typePin = typePin;
        this.pin = pin;
        this.portStatus = portStatus;
    }

    public Device getDevice() {
        return device;
    }

    public TypePin getTypePin() {
        return typePin;
    }

    public int getPin() {
        return pin;
    }

    public int getSignalOnPort() {
        assert (typePin == TypePin.ANALOG);
        return signalOnPort;
    }

    public PortStatus getPortStatus() {
        assert (typePin == TypePin.DIGITAL);
        return portStatus;
    }


    public JsonObject toJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(ActionAPI.FOR_DEVICE.getJsonExtra(), device.toString());
        jsonObject.addProperty(ActionAPI.PIN_TYPE.getJsonExtra(), typePin.toString());
        jsonObject.addProperty(ActionAPI.PIN_ID.getJsonExtra(), pin);

        if (typePin == TypePin.ANALOG)
            jsonObject.addProperty(ActionAPI.PIN_VALUE.getJsonExtra(), signalOnPort);
        else
            jsonObject.addProperty(ActionAPI.PIN_STATUS.getJsonExtra(), portStatus.getJsonExtra());

        return jsonObject;
    }

    @Override
    public String toString() {
        return "Action{" +
                "device=" + device +
                ", typePin=" + typePin +
                ", pin=" + pin +
                ", signalOnPort=" + signalOnPort +
                ", portStatus=" + portStatus +
                '}';
    }

    public boolean isEquals(Action a) {
        return a.device == this.device
                && a.typePin == this.typePin
                && a.pin == this.pin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Action action = (Action) o;
        return pin == action.pin &&
                signalOnPort == action.signalOnPort &&
                typePin == action.typePin &&
                portStatus == action.portStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(typePin, pin, signalOnPort, portStatus);
    }

    public static JsonObject getApiActionAsJsonObject(Action action)
    {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(ActionAPI.PIN_ID.getJsonExtra(),action.getPin());
        jsonObject.addProperty(ActionAPI.PIN_TYPE.getJsonExtra(),action.getTypePin().toString());
        jsonObject.addProperty(ActionAPI.FOR_DEVICE.getJsonExtra(),action.getDevice().toString());

        if(action.getTypePin() == TypePin.DIGITAL)
            jsonObject.addProperty(ActionAPI.PIN_STATUS.getJsonExtra(),action.getPortStatus().getJsonExtra());
        else if(action.getTypePin() == TypePin.ANALOG)
            jsonObject.addProperty(ActionAPI.PIN_VALUE.getJsonExtra(),action.getSignalOnPort());

        return jsonObject;
    }

}
