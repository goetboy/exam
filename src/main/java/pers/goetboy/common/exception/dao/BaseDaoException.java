package pers.goetboy.common.exception.dao;

import pers.goetboy.common.exception.BaseException;

/**
 * 针对于dao层的异常抛出
 * @author goetb
 */
public class BaseDaoException extends BaseException {

    private static final long serialVersionUID = 1L;

    public BaseDaoException() {
        super();
    }

    public BaseDaoException(String msg) {
        super(msg);
    }

    public BaseDaoException(String msg, Exception ex) {
        super(msg, ex);
    }

    public BaseDaoException(Exception ex) {
        super("数据操作异常", ex);
    }
}
