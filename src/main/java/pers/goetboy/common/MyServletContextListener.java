package pers.goetboy.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 容器启动和销毁时执行
 *
 * @author goetb
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {

    /**
     * 容器初始化
     *
     * @param event
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();

    }

    /**
     * 容器销毁
     *
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
