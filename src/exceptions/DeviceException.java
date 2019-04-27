package exceptions;

public class DeviceException extends Exception
{
    public DeviceException() {
        super("Wrong Device!");
    }

    public DeviceException(String message) {
        super("Wrong Device:"+message);
    }
}
