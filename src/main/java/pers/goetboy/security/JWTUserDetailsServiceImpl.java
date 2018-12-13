package pers.goetboy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pers.goetboy.entity.sys.Role;
import pers.goetboy.entity.sys.User;
import pers.goetboy.services.LoginRepository;
import pers.goetboy.services.RoleService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component("jwtUserDetail")
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
            user.setRoles(loadUserRoles(user.getId()));
            return user;

        }
    }


    public List<Role> loadUserRoles(Long userId) {
        List<Role> roles = roleService.listRoleByUser(userId);
        if (roles == null || roles.isEmpty()) {
            return null;
        } else {
            return roles;
        }

    }
}
