package pers.goetboy.common.exception.common.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * service日志切面
 */
@Aspect
public class LogAdvice {
    Logger logger = LoggerFactory.getLogger(LogAdvice.class);

    @Pointcut("execution(public * pers.goetboy.services.*.*(..))")
    public void serviceAop(){}

    @Around("serviceAop()")
    public void print(ProceedingJoinPoint point) throws Throwable {
        String methodName=point.getSignature().getName();
        String className=point.getTarget().getClass().getName();
        logger.info("{}.{} start...",className,methodName);
        logger.info(Arrays.toString(point.getArgs()));
        Object object = point.proceed();
        logger.info(object.toString());
        logger.info("{}.{} end...",className,methodName);
    }

}
