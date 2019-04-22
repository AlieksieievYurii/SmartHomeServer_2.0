package exceptions;

public class PortException extends Exception {

    public PortException(int port) {
        super("Wrong port: " + port);
    }
}
