package exceptions;

public class StatusTaskException extends Exception
{
    public StatusTaskException(String message) {
        super("Wrong status components.task: " + message);
    }
}
