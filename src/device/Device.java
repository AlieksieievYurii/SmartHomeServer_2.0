package device;

public enum Device
{
    TCOD("tcod"),WCOD("test");

    private String device;

    Device(String device) {
        this.device = device;
    }

    public String getDevice() {
        return device;
    }

    public static Device whatDevice(String device)
    {
        if ("tcod".equals(device)) {
            return TCOD;
        }else if("test".equals(device))
            return WCOD;
        return null;
    }


}
