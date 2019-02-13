package pers.goetboy.security;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import pers.goetboy.entity.STATE_ENUM;
import pers.goetboy.entity.sys.Menu;
import pers.goetboy.entity.sys.Role;
import pers.goetboy.mapper.MenuMapper;
import pers.goetboy.mapper.RoleMapper;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * 菜单校验
 *
 * @author:goetboy
 * @date 2019 /01 /23
 **/
@Log4j2
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private final static String[] whiteList = new String[]{"/login/**", "/druid/**"};
    @Autowired
    MenuMapper menuMapper;
    @Autowired
    RoleMapper roleMapper;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        final AntPathMatcher antPathMatcher = new AntPathMatcher();
        FilterInvocation fi = (FilterInvocation) object;
        StringBuffer roleStr = new StringBuffer();
        String url = fi.getRequestUrl();
        System.out.println(url);

        //白名单
        for (String s : whiteList) {
            if (antPathMatcher.matchStart(s, url)) {
                return SecurityConfig.createList(roleStr.append("Role").toString());
            }
        }
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        //校验角色权限
        SecurityContextHolder.getContext().getAuthentication().getAuthorities().forEach(authority ->
        {
            //获取角色信息


            Role role = roleMapper.selectOne(Wrappers.<Role>lambdaQuery().eq(Role::getState, STATE_ENUM.NORMAL.getValue()).eq(Role::getName, authority.getAuthority()));
            if (role != null) {
                List<Menu> menus = menuMapper.selectByRoleId(role.getId());

                if (CollectionUtils.isNotEmpty(menus)) {

                   Optional<Menu> optionalMenu = menus.stream().filter(menu -> antPathMatcher.matchStart(menu.getUrl(), url)).findFirst();
                    if (optionalMenu.isPresent()) {
                        roleStr.append("," + authority.getAuthority());
                    }
                }
            }
        });

        if (roleStr.length() > 0) {
            return SecurityConfig.createList(roleStr.toString());
        } else {
            log.error("没有匹配到URL");
            throw new AccessDeniedException("not allow");
        }
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
