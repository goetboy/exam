package pers.goetboy;

import com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 启动类
 *
 * @author goetb
 */
@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan(basePackages = "pers.goetboy.*.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Bean
    public OracleKeyGenerator oracleKeyGenerator(){
        return  new OracleKeyGenerator();
    }
}

