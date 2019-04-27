package exceptions;

public class TimerException extends Exception
{
    public TimerException(String message) {
        super("Wrong time format:" + message);
    }
}
