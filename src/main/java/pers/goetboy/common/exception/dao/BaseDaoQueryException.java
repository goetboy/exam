package pers.goetboy.common.exception.dao;

/**
 * dao层异常抛出的派生子类，主要抛出select异常
 */
public class BaseDaoQueryException extends BaseDaoException {

    private static final long serialVersionUID = 1L;

    public BaseDaoQueryException() {
        super();
    }

    public BaseDaoQueryException(String msg) {
        super(msg);
    }

    public BaseDaoQueryException(String msg, Exception ex) {
        super(msg, ex);
    }

    public BaseDaoQueryException(Exception ex) {
        super("查询异常", ex);
    }

}
