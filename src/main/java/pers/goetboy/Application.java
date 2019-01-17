package pers.goetboy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.Properties;

/**
 * 等同于默认的属性的
 *
 * @Configuration 配置
 * @EnableAutoConfiguration 开启自动配置
 * @ComponentScan 扫描  注意，@ComponentScan 不能凭空使用。
 */
//@MapperScan("pers.goetboy.mapper")
@MapperScan(basePackages = "pers.goetboy.mapper")
@SpringBootApplication
@ServletComponentScan
@EnableAspectJAutoProxy

public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

   /*@Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("reasonable","true");
        properties.setProperty("dialect","mysql");    //配置mysql数据库的方言
        pageHelper.setProperties(properties);
        return pageHelper;
    }*/
}

