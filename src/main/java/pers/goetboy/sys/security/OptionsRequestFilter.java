package pers.goetboy.sys.security;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * options 请求过滤
 *
 * @author:goetboy;
 * @date 2018 /12 /14
 **/
@Deprecated
public class OptionsRequestFilter extends OncePerRequestFilter {
    private static final String REQUEST_METHOD = "OPTIONS";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (REQUEST_METHOD.equals(request.getMethod())) {
            response.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,HEAD");
            response.setHeader("Access-Control-Allow-Headers", response.getHeader("Access-Control-Request-Headers"));
            return;
        }
        filterChain.doFilter(request, response);
    }
}
