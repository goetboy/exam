package pers.goetboy.common;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * spring 初始化 listener
 *
 * @author goetb
 * @date 2019年1月14日
 */
@Slf4j
@WebListener
public class SpringLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("###########################\r")
                .append("Sprign 初始化 \r")
                .append("Bean number:").append(ctx.getBeanDefinitionCount())
                .append("\r")
                .append("Bean Names:\r");
        for (String beanDefinitionName : ctx.getBeanDefinitionNames()) {
            stringBuffer.append(beanDefinitionName)
                    .append("\r");
        }
        stringBuffer.append("###########################");
        log.info(stringBuffer.toString());
        SpringContextUtil.setContext(ctx);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }


}
