package pers.goetboy.common.exception;

/**
 * 针对于控制器的异常抛出,一般抛出代号
 */
public class BaseControllerException extends BaseException {

    private static final long serialVersionUID = 1L;

  
    public BaseControllerException() {
        super();
    }

    ;

    public BaseControllerException(String msg) {
        super(msg);
    }

    public BaseControllerException(String msg, Exception ex) {
        super(msg, ex);
    }
    
    public BaseControllerException(Exception ex) {
        super("控制器异常", ex);
    }


}
