package exceptions;

public class TypeTaskException extends Exception {
    public TypeTaskException(String message) {
        super("Wrong TypeTask:" + message);
    }
}
