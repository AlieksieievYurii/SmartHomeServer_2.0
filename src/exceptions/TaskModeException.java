package exceptions;

public class TaskModeException extends Exception
{
    public TaskModeException(String message) {
        super("Wrong task mode:" + message);
    }
}
