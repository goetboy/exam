package pers.goetboy.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import pers.goetboy.services.MenuService;

import java.util.Collection;
import java.util.List;

public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private  static  final Logger logger = LoggerFactory.getLogger(UrlFilterInvocationSecurityMetadataSource.class);

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
      /*  logger.info("获取权限");
        //获取请求地址
        String requestUrl = ((FilterInvocation) o).getRequest().getRequestURI();
        if ("/login".equals(requestUrl)||"/error".equals(requestUrl)) {
            return null;
        }

        List<SysMenu> allMenu = sysMenuService.listAll();
        for (SysMenu menu : allMenu) {
            if (antPathMatcher.match(menu.getUrl(), requestUrl)&&menu.getRoles().size()>0) {
               List<SysRole> roles = menu.getRoles();
                int size = roles.size();
                String[] values = new String[size];
                for (int i = 0; i < size; i++) {
                values[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(values);
            }
        }*/
        //没有匹配上的资源，都是登录访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
