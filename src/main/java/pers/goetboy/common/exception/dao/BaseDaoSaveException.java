package pers.goetboy.common.exception.dao;

/**
 * dao层异常抛出的派生子类，主要抛出save异常
 */
public class BaseDaoSaveException extends BaseDaoException {

    private static final long serialVersionUID = 1L;

    public BaseDaoSaveException() {
        super();
    }

    public BaseDaoSaveException(String msg) {
        super(msg);
    }

    public BaseDaoSaveException(String msg, Exception ex) {
        super(msg, ex);
    }

    public BaseDaoSaveException(Exception ex) {
        super("保存异常", ex);
    }


}
