package exceptions;

public class PortTypeException extends Exception
{
    public PortTypeException() {
        super("Wrong portType!");
    }

    public PortTypeException(String message) {
        super("Wrong portType:" + message);
    }
}
