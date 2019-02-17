package pers.goetboy.common.exception.dao;

/**
 *dao层异常抛出的派生子类，主要抛出update异常
 */
public class BaseDaoUpdateException extends BaseDaoException{

	private static final long serialVersionUID = 1L;

	public BaseDaoUpdateException() {
		super();
	}
	public BaseDaoUpdateException(String msg) {
		super(msg);
	}
	public BaseDaoUpdateException(String msg, Exception ex) {
		super(msg, ex);
	}
	public BaseDaoUpdateException(Exception ex) {
		super("更新异常", ex);
	}
	
}
