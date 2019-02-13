package pers.goetboy.common.advice;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * service日志切面
 *
 * @author goetb
 */
@Component
@Aspect
@Log4j2
public class ServiceAdvice {

    @Pointcut("execution(* pers.goetboy.common.AbstractService+.*(..))")
    public void serviceAop() {
    }
    @Around("serviceAop()")
    public Object print(ProceedingJoinPoint point) throws Throwable {

        String methodName = point.getSignature().getName();
        String className = point.getTarget().getClass().getName();
        log.info("{}.{} start...", className, methodName);
        log.info("param:{}", Arrays.toString(point.getArgs()));
        Object result;

        result = point.proceed();

        if (result != null) {
            log.info("return:{{}}", result.toString());
        }

        log.info("{}.{} end...", className, methodName);
        return result;
    }


}
