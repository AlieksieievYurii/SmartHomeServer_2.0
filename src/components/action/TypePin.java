package components.action;

import exceptions.PortTypeException;

public enum TypePin
{
    DIGITAL("digital"),ANALOG("analog");


    private String typePort;

    TypePin(String typePort) {
        this.typePort = typePort;
    }

    public String toString() {
        return typePort;
    }

    public static TypePin getPortType(String typePin) throws PortTypeException {
        if(typePin.equals("digital"))
            return DIGITAL;
        else if(typePin.equals("analog"))
            return ANALOG;
        else
            throw new PortTypeException(typePin);
    }
}
