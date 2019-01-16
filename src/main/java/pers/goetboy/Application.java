package pers.goetboy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 等同于默认的属性的
 *
 * @Configuration 配置
 * @EnableAutoConfiguration 开启自动配置
 * @ComponentScan 扫描  注意，@ComponentScan 不能凭空使用。
 */
@MapperScan("pers.goetboy.mapper")
@SpringBootApplication
@ServletComponentScan
@EnableAspectJAutoProxy
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

