package pers.goetboy.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author:goetboy;
 * @date 2018 /12 /07
 **/
@Component
@ConfigurationProperties(prefix = "jwt")
@PropertySource("classpath:config/jwt.properties")
@Data
public class JWTConfig {
    /**
     * url Header名称
     */

    private String header;
    /**
     * 密钥
     */
    private String secret;
    /**
     * 过期时间
     */
    private Long expiration;
    /**
     * token头
     */
    private String tokenHead;
    /**
     * 期望的url
     */
    private String exceptUrl;



}
