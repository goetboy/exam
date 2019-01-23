package pers.goetboy.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;

/**
 * @author:goetb
 * @date 2019 /01 /23
 **/
@Log4j2
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    //配置文件的加载
    private static String urlRoleMap;

    static {
        Properties prop = new Properties();
        InputStream in = Object.class.getResourceAsStream("/spring-security.properties");
        log.error("加载URL的配置文件");
        try {
            prop.load(in);
            urlRoleMap = prop.getProperty("urls").trim();

        } catch (IOException e) {
            log.error("spring-security.properties配置路径不存在{}", e.getMessage());
            e.printStackTrace();
        }
    }

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();


    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation fi = (FilterInvocation) object;
        StringBuffer roles = new StringBuffer("START");
        String url = fi.getRequestUrl();
//    String httpMethod = fi.getRequest().getMethod();
        ObjectMapper jsonObject = null;
        try {
            //jsonObject = JSONObject.parseObject(urlRoleMap);
        } catch (Exception e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        String value = "";
        //遍历json
       /* for (String key : jsonObject.keySet()) {
            if (antPathMatcher.match(key, url)) {
              //  value = jsonObject.getString(key);
                //如果有，号的就说明是多个角色
                roles.append("," + value);
            }
        }*/
        if (!("START").equals(roles)) {
            return SecurityConfig.createList(roles.toString());
        } else {
            log.error("没有匹配到URL");
            //没有匹配到
            // return SecurityConfig.createList("NULL");
            throw new AccessDeniedException("not allow");
        }

        //    return null;
    }


    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
