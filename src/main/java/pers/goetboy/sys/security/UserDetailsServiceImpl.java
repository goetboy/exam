package pers.goetboy.sys.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pers.goetboy.sys.mapper.RoleMapper;
import pers.goetboy.sys.mapper.UserMapper;
import pers.goetboy.sys.model.entity.Role;
import pers.goetboy.sys.model.entity.User;

import java.util.List;

/**
 * userDetailsService实现类
 *
 * @author:goetb
 * @date 2019 /01 /25
 **/
@Service("jwtUserDetail")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    @Autowired
    public UserDetailsServiceImpl(UserMapper userMapper, RoleMapper roleMapper) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            user.setRoles(loadUserRoles(user.getId()));
            return user;
        }
    }

    private List<Role> loadUserRoles(Long userId) {
        List<Role> roles = roleMapper.selectByUserId(userId);
        if (roles == null || roles.isEmpty()) {
            return null;
        } else {
            return roles;
        }

    }
}
