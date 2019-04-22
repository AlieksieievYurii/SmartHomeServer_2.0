package exceptions;

public class SignalException extends Exception
{
    public SignalException(int signal) {
        super("Wrong signal: " + signal + ". Allowable signal: 0...255");
    }
}
