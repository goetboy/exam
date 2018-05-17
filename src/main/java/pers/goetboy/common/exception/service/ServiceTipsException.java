package pers.goetboy.common.exception.service;

/**
 * 业务层消息弹出异常
 */
public class ServiceTipsException extends BaseServiceException {
    public ServiceTipsException() {
        super();
    }

    public ServiceTipsException(String message) {

        super(message);
    }

    public ServiceTipsException(Throwable cause) {

        super(cause);
    }

    public ServiceTipsException(String message, Throwable cause) {
        super(message, cause);
    }

    protected ServiceTipsException(String message, Throwable cause,
                                   boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
