package pers.goetboy.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import pers.goetboy.common.exception.redis.RedisClient;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 这里可以将用户写入redis
 */
@Component
public class TokenAuthenticationService {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static final long EXPIRATIONTIME = 1000 * 60 * 60 * 24 * 1; // 1 days
    private static final String SECRET = "ThisIsASecret";
    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";

    private static RedisClient redisClient;
    @Autowired
    private RedisClient redisClientTemp;

    public void setRedisClientTemp(RedisClient redisClientTemp) {
        this.redisClientTemp = redisClientTemp;
    }

    @PostConstruct
    public void init() {

        TokenAuthenticationService.redisClient = this.redisClientTemp;
    }


    static void addAuthentication(HttpServletResponse res, Authentication user) throws JsonProcessingException {
        String tokenId = UUID.randomUUID().toString();
        String JWT = Jwts.builder()
                .setSubject(tokenId)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);

        redisClient.set(tokenId + "@user", user.getPrincipal(), EXPIRATIONTIME);
        redisClient.set(tokenId + "@role", user.getAuthorities(), EXPIRATIONTIME);
       /* *//**
         * 配置登陆支持跨域
         *//*
        res.setHeader("Content-type", "text/html;charset=UTF-8");
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Max-Age", "1800");
        res.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS");*/
    }

    static Authentication getAuthentication(HttpServletRequest request) throws IOException {
        String token = request.getHeader(HEADER_STRING);
        if (org.springframework.util.StringUtils.isEmpty(token))
            return null;

        // parse the token.
        String userToken = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();
        if (!redisClient.hasKey(userToken + "@user"))
            return null;

        UserDetails userDetails = (UserDetails) redisClient.get(userToken + "@user");
        List<SimpleGrantedAuthority> roles = (List<SimpleGrantedAuthority>) redisClient.get(userToken + "@role");

        return new UsernamePasswordAuthenticationToken(userDetails, null, roles);
        //  return user != null ?                    new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()) :                    null;
        // return user != null ? objectMapper.readValue(user, Authentication.class) : null;


    }
}
