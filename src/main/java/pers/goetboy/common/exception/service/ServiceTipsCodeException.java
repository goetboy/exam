package pers.goetboy.common.exception.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import pers.goetboy.services.BaseService;

/**
 * @author:goetboy;
 * @date 2018 /12 /07
 **/
public class ServiceTipsCodeException extends BaseServiceException {
    @Autowired
    private static Environment env;
    /**
     * 异常码
     */
    private String code;

    public ServiceTipsCodeException() {

        super();
    }

    public ServiceTipsCodeException(String code) {

        super(env.getProperty(code));
    }

    public ServiceTipsCodeException(Throwable cause) {

        super(cause);
    }

    public ServiceTipsCodeException(String code, Throwable cause) {
        super(env.getProperty(code), cause);
    }

    protected ServiceTipsCodeException(String code, Throwable cause,
                                       boolean enableSuppression,
                                       boolean writableStackTrace) {
        super(env.getProperty(code), cause, enableSuppression, writableStackTrace);
    }
}
