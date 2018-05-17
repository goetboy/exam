package pers.goetboy.common.exception;

public class BaseException extends Exception {
    public BaseException() {
        super();
    }

    public BaseException(String message) {

        super(message);
    }

    public BaseException(Throwable cause) {

        super(cause);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    protected BaseException(String message, Throwable cause,
                            boolean enableSuppression,
                            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}