package pers.goetboy.sys.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;

import java.util.Collection;

/**
 * @author:goetb
 * @date 2019 /01 /23
 **/
public class MyAccessDecisionManager implements AccessDecisionManager {


    @Override
    public void decide(Authentication authentication, Object object,
                       Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {

        for (ConfigAttribute ca : configAttributes) {

            String needRole = ca.getAttribute();
            if (StringUtils.isNotBlank(needRole)) {
                return;
            }

        }
        throw new AccessDeniedException("not allow");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
