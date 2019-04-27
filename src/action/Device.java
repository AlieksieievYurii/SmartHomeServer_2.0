package action;

import exceptions.DeviceException;

public enum Device
{
    TCOD("tcod"),WCOD("twcod");

    private String device;

    Device(String device) {
        this.device = device;
    }

    public String toString() {
        return device;
    }

    public static Device whatDevice(String device) throws DeviceException {
        if ("tcod".equals(device)) {
            return TCOD;
        }else if("twcod".equals(device))
            return WCOD;
        else
            throw new DeviceException(device);
    }


}
