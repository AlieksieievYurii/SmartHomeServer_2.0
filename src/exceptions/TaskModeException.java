package exceptions;

public class TaskModeException extends Exception
{
    public TaskModeException(String message) {
        super("Wrong components.task mode:" + message);
    }
}
