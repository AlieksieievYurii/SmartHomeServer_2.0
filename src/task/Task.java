package task;

import com.google.gson.JsonObject;

public class Task
{
    private PortType portType;
    private int port;
    private int portStatus;


    public Task(PortType portType, int port, int portStatus) {
        this.portType = portType;

        assert (port >= 0);
        this.port = port;

        assert (portStatus > 0 && portStatus <= 255);
        this.portStatus = portStatus;
    }

    public PortType getPortType() {
        return portType;
    }

    public int getPort() {
        return port;
    }

    public int getPortStatus() {
        return portStatus;
    }

    public JsonObject toJsonObject()
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(TaskExtra.TYPE_PORT.getJsonExtra(),portType.getTypePort());
        jsonObject.addProperty(TaskExtra.PORT.getJsonExtra(),port);
        jsonObject.addProperty(TaskExtra.STATUS_PORT.getJsonExtra(),portStatus);

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
