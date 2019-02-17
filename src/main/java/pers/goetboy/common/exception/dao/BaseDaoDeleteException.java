package pers.goetboy.common.exception.dao;

/**
 * dao层异常抛出的派生子类，主要抛出delete异常
 */
public class BaseDaoDeleteException extends  BaseDaoException{
	
	private static final long serialVersionUID = 1L;


	public BaseDaoDeleteException(){
		super();
	};
	public BaseDaoDeleteException(String msg){
		super(msg);
	};
	public  BaseDaoDeleteException(String msg,Exception ex){
		super(msg,ex);
	}
	public  BaseDaoDeleteException(Exception ex){		super("删除异常",ex);
	}
}
