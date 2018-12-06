package pers.goetboy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.context.support.SecurityWebApplicationContextUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pers.goetboy.entity.sys.Role;
import pers.goetboy.entity.sys.User;
import pers.goetboy.services.LoginRepository;
import pers.goetboy.services.RoleService;
import sun.plugin.liveconnect.SecurityContextHelper;

import java.util.List;
import java.util.stream.Collectors;

@Service("jwtUserDetail")
public class JWTUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = loginRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            List<Role> roles = roleService.listRoleByUser(user.getId());
            if (roles == null || roles.isEmpty()) {
                return new JWTUser(user.getUsername(), user.getPassword(), null);
            } else {
                return new JWTUser(user.getUsername(), user.getPassword(), roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
            }
        }
    }

}
