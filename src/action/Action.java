package action;

import com.google.gson.JsonObject;
import exceptions.*;

import java.util.Objects;

public class Action {
    private Device device;
    private PortType portType;
    private int port;
    private int signalOnPort;
    private PortStatus portStatus;

    public Action(Device device, PortType portType, int port, int signalOnPort) throws SignalException {
        this.device = device;
        this.portType = portType;
        this.port = port;
        if(signalOnPort >= 0 && signalOnPort <= 255)
            this.signalOnPort = signalOnPort;
        else
            throw new SignalException(signalOnPort);
    }

    public Action(Device device, PortType portType, int port, PortStatus portStatus) {
        this.device = device;
        this.portType = portType;
        this.port = port;
        this.portStatus = portStatus;
    }

    public Device getDevice() {
        return device;
    }

    public PortType getPortType() {
        return portType;
    }

    public int getPort() {
        return port;
    }

    public int getSignalOnPort() {
        assert (portType == PortType.ANALOG);
        return signalOnPort;
    }

    public PortStatus getPortStatus() {
        assert (portType == PortType.DIGITAL);
        return portStatus;
    }


    public JsonObject toJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(ActionExtra.FOR_DEVICE.getJsonExtra(), device.toString());
        jsonObject.addProperty(ActionExtra.TYPE_PORT.getJsonExtra(), portType.toString());
        jsonObject.addProperty(ActionExtra.PORT.getJsonExtra(), port);

        if (portType == PortType.ANALOG)
            jsonObject.addProperty(ActionExtra.SIGNAL_ON_PORT.getJsonExtra(), signalOnPort);
        else
            jsonObject.addProperty(ActionExtra.STATUS_PORT.getJsonExtra(), portStatus.getJsonExtra());

        return jsonObject;
    }

    @Override
    public String toString() {
        return "Action{" +
                "device=" + device +
                ", portType=" + portType +
                ", port=" + port +
                ", signalOnPort=" + signalOnPort +
                ", portStatus=" + portStatus +
                '}';
    }

    public boolean isEquals(Action a) {
        return a.device == this.device
                && a.portType == this.portType
                && a.port == this.port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Action action = (Action) o;
        return port == action.port &&
                signalOnPort == action.signalOnPort &&
                portType == action.portType &&
                portStatus == action.portStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(portType, port, signalOnPort, portStatus);
    }

    public static JsonObject getApiActionAsJsonObject(Action action)
    {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(ApiActionExtras.PORT_ID.getJsonExtra(),action.getPort());
        jsonObject.addProperty(ApiActionExtras.PORT_TYPE.getJsonExtra(),action.getPortType().toString());
        jsonObject.addProperty(ApiActionExtras.FOR_DEVICE.getJsonExtra(),action.getDevice().toString());

        if(action.getPortType() == PortType.DIGITAL)
            jsonObject.addProperty(ApiActionExtras.PORT_STATUS.getJsonExtra(),action.getPortStatus().getJsonExtra());
        else if(action.getPortType() == PortType.ANALOG)
            jsonObject.addProperty(ApiActionExtras.PORT_VALUE.getJsonExtra(),action.getSignalOnPort());

        return jsonObject;
    }

}
