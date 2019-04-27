package exceptions;

public class DateException  extends Exception {
    public DateException(String message) {
        super("Wrong date format:" + message);
    }
}
