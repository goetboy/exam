package pers.goetboy.common;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * controller抽象类
 * @author goetb
 */
public abstract class AbstractController {

    /**
     * 取session
     *
     * @return session
     */
    protected HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 取request
     *
     * @return request
     */
    protected HttpServletRequest getRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return Objects.requireNonNull(attrs).getRequest();
    }

    /**
     * 取response
     *
     * @return response
     */
    protected HttpServletResponse getResponse() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        return Objects.requireNonNull(attrs).getResponse();
    }
}
