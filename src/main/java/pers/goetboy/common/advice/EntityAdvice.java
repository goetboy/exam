package pers.goetboy.common.advice;

import org.apache.ibatis.mapping.MappedStatement;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author:goetb
 * @date 2019 /01 /17
 **/
@Aspect
@Component
public class EntityAdvice {
    @Before("execution(* pers.goetboy.mapper.*.insert*(..))")
    public void before(JoinPoint point) {
        MappedStatement ms = (MappedStatement) point.getArgs()[0];
        System.out.println(ms);
        System.out.println(ms.getParameterMap());
    }
}
