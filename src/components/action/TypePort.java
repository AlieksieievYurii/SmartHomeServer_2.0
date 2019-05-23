package components.action;

import exceptions.PortTypeException;

public enum TypePort
{
    DIGITAL("digital"),ANALOG("analog");


    private String typePort;

    TypePort(String typePort) {
        this.typePort = typePort;
    }

    public String toString() {
        return typePort;
    }

    public static TypePort getPortType(String portType) throws PortTypeException {
        if(portType.equals("digital"))
            return DIGITAL;
        else if(portType.equals("analog"))
            return ANALOG;
        else
            throw new PortTypeException(portType);
    }
}
