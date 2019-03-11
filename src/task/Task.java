package task;

import com.google.gson.JsonObject;

public class Task
{

    private PortType portType;
    private int port;
    private int signalOnPort;
    private PortStatus portStatus;

    public Task(PortType portType, int port, int signalOnPort) {
        assert (portType == PortType.ANALOG);
        assert (port > 0);
        assert (signalOnPort >= 0 && signalOnPort <= 255);

        this.portType = portType;
        this.port = port;
        this.signalOnPort = signalOnPort;
    }

    public Task(PortType portType, int port, PortStatus portStatus) {
        assert(portType == PortType.DIGITAL);
        assert (port > 0);
        this.portType = portType;
        this.port = port;
        this.portStatus = portStatus;
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
        assert(portType == PortType.DIGITAL);
        return portStatus;
    }

    public JsonObject toJsonObject()
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(TaskExtra.TYPE_PORT.getJsonExtra(),portType.getTypePort());
        jsonObject.addProperty(TaskExtra.PORT.getJsonExtra(),port);

        if(portType == PortType.ANALOG)
            jsonObject.addProperty(TaskExtra.SIGNAL_ON_PORT.getJsonExtra(),signalOnPort);
        else
            jsonObject.addProperty(TaskExtra.STATUS_PORT.getJsonExtra(),portStatus.getJsonExtra());

        return jsonObject;
    }

    @Override
    public String toString() {
        return "Task{" +
                "portType=" + portType +
                ", port=" + port +
                ", portStatus=" + portStatus +
                '}';
    }
}
