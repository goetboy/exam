package pers.goetboy.common.exception.service;

import pers.goetboy.common.exception.BaseException;

/**
 * 业务层异常
 */
public class BaseServiceException extends BaseException {
    public BaseServiceException() {
        super();
    }

    public BaseServiceException(String message) {

        super(message);
    }

    public BaseServiceException(Throwable cause) {

        super(cause);
    }

    public BaseServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    protected BaseServiceException(String message, Throwable cause,
                            boolean enableSuppression,
                            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
