package pers.goetboy.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;

/**
 * sprng 上下文工具类
 */

public class SpringContextUtil {

    private static ApplicationContext context;

    public static ApplicationContext getContext() {
        return context;
    }

    public static void setContext(ApplicationContext context) {
        SpringContextUtil.context = context;
    }

    public static Object getBean(String name) throws BeansException {
        return context.getBean(name);
    }

    public static Object getBean(String name, Class<?> requiredType)
            throws BeansException {
        return context.getBean(name, requiredType);
    }

    public static boolean containsBean(String name) {
        return context.containsBean(name);
    }

    public static boolean isSingleton(String name)
            throws NoSuchBeanDefinitionException {
        return context.isSingleton(name);
    }

    public static Class<?> getType(String name)
            throws NoSuchBeanDefinitionException {
        return context.getType(name);
    }

    public static String[] getAliases(String name)
            throws NoSuchBeanDefinitionException {
        return context.getAliases(name);
    }
}
