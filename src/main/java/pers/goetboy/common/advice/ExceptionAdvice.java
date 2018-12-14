package pers.goetboy.common.advice;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pers.goetboy.common.exception.service.ServiceTipsException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
@Log4j2
public class ExceptionAdvice  {
    /**
     * 统一异常处理
     * @param ex
     * @param request
     * @param response
     * @throws IOException
     */
    @ExceptionHandler(Exception.class)
    public void exception(Exception ex, HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.error("========接口产生异常========");
        log.error(ex);
        log.error("===========================");
        //TODO 还可以使用ResponseEntity 未测试
        String msg="";
        if(ex instanceof ServiceTipsException)
        {
            msg=ex.getMessage();
        }else if(ex instanceof Exception){
            msg="操作异常";
        }
        response.setStatus(500);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(msg);
    }
}
