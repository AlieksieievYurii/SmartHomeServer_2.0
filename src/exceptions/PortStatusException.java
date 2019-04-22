package exceptions;

public class PortStatusException extends Exception
{
    public PortStatusException() {
        super("NullPointException in PortStatus. PortStatus can not be null!");
    }
}
