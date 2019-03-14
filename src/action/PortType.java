package action;

public enum PortType
{
    DIGITAL("digital"),ANALOG("analog");


    private String typePort;

    PortType(String typePort) {
        this.typePort = typePort;
    }

    public String toString() {
        return typePort;
    }

    public static PortType getPortType(String portType)
    {
        if(portType.equals("digital"))
            return DIGITAL;
        else if(portType.equals("analog"))
            return ANALOG;
        else
            return null;
    }
}
