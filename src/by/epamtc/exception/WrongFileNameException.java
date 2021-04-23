package by.epamtc.exception;

public class WrongFileNameException extends Exception {

    public WrongFileNameException() {
    }

    public WrongFileNameException(String message) {
        super(message);
    }

    public WrongFileNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongFileNameException(Throwable cause) {
        super(cause);
    }

}
