package exceptions;

public class TypeRequestException extends Exception {
    public TypeRequestException(String message) {
        super("Wrong typeRequest:" + message);
    }
}
