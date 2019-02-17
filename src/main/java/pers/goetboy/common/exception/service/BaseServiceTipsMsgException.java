package pers.goetboy.common.exception.service;

import pers.goetboy.common.exception.BaseException;

/**
 * service异常，主要用于抛出自定义异常抛出
 */
public class BaseServiceTipsMsgException extends BaseException {

    private static final long serialVersionUID = 1L;

    public BaseServiceTipsMsgException() {
        super();
    }

    public BaseServiceTipsMsgException(String msg) {
        super(msg);
    }

    public BaseServiceTipsMsgException(String msg, Exception ex) {
        super(msg, ex);
    }

    public BaseServiceTipsMsgException(Exception ex) {
        super("提示异常", ex);
    }

}
