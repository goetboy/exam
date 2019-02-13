package pers.goetboy.common.advice;

import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pers.goetboy.common.exception.BaseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 异常拦截
 *
 * @author goetb
 */
@ControllerAdvice
@Log4j2
public class ExceptionAdvice {

    /**
     * 统一异常处理
     *
     * @param ex
     * @param request
     * @param response
     * @throws IOException
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity exceptionHandler(Exception ex, HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.error("========接口产生异常========");
        log.error("异常信息:", ex);
        log.error("===========================");
        String msg = "";
        Integer status = 1001;
        if (ex instanceof BaseException) {
            msg = ex.getMessage();
        } else if (ex instanceof Exception) {
            msg = "操作异常";
        }
        return ResponseEntity.status(status).body(msg);
    }
}
