package components.action;

import com.google.gson.JsonObject;
import exceptions.*;

import java.util.Objects;

public class Action {
    private Device device;
    private TypePort typePort;
    private int port;
    private int signalOnPort;
    private PortStatus portStatus;

    public Action(Device device, TypePort typePort, int port, int signalOnPort) throws SignalException {
        this.device = device;
        this.typePort = typePort;
        this.port = port;
        if(signalOnPort >= 0 && signalOnPort <= 255)
            this.signalOnPort = signalOnPort;
        else
            throw new SignalException(signalOnPort);
    }

    public Action(Device device, TypePort typePort, int port, PortStatus portStatus) {
        this.device = device;
        this.typePort = typePort;
        this.port = port;
        this.portStatus = portStatus;
    }

    public Device getDevice() {
        return device;
    }

    public TypePort getTypePort() {
        return typePort;
    }

    public int getPort() {
        return port;
    }

    public int getSignalOnPort() {
        assert (typePort == TypePort.ANALOG);
        return signalOnPort;
    }

    public PortStatus getPortStatus() {
        assert (typePort == TypePort.DIGITAL);
        return portStatus;
    }


    public JsonObject toJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(ActionExtra.FOR_DEVICE.getJsonExtra(), device.toString());
        jsonObject.addProperty(ActionExtra.TYPE_PORT.getJsonExtra(), typePort.toString());
        jsonObject.addProperty(ActionExtra.PORT.getJsonExtra(), port);

        if (typePort == TypePort.ANALOG)
            jsonObject.addProperty(ActionExtra.SIGNAL_ON_PORT.getJsonExtra(), signalOnPort);
        else
            jsonObject.addProperty(ActionExtra.STATUS_PORT.getJsonExtra(), portStatus.getJsonExtra());

        return jsonObject;
    }

    @Override
    public String toString() {
        return "Action{" +
                "device=" + device +
                ", typePort=" + typePort +
                ", port=" + port +
                ", signalOnPort=" + signalOnPort +
                ", portStatus=" + portStatus +
                '}';
    }

    public boolean isEquals(Action a) {
        return a.device == this.device
                && a.typePort == this.typePort
                && a.port == this.port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Action action = (Action) o;
        return port == action.port &&
                signalOnPort == action.signalOnPort &&
                typePort == action.typePort &&
                portStatus == action.portStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(typePort, port, signalOnPort, portStatus);
    }

    public static JsonObject getApiActionAsJsonObject(Action action)
    {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(ApiActionExtras.PORT_ID.getJsonExtra(),action.getPort());
        jsonObject.addProperty(ApiActionExtras.PORT_TYPE.getJsonExtra(),action.getTypePort().toString());
        jsonObject.addProperty(ApiActionExtras.FOR_DEVICE.getJsonExtra(),action.getDevice().toString());

        if(action.getTypePort() == TypePort.DIGITAL)
            jsonObject.addProperty(ApiActionExtras.PORT_STATUS.getJsonExtra(),action.getPortStatus().getJsonExtra());
        else if(action.getTypePort() == TypePort.ANALOG)
            jsonObject.addProperty(ApiActionExtras.PORT_VALUE.getJsonExtra(),action.getSignalOnPort());

        return jsonObject;
    }

}
