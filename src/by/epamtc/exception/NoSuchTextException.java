package by.epamtc.exception;

public class NoSuchTextException extends Exception {

    public NoSuchTextException() {
    }

    public NoSuchTextException(String message) {
        super(message);
    }

    public NoSuchTextException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchTextException(Throwable cause) {
        super(cause);
    }

}
