package pers.goetboy.services;

import com.goetboy.core.exception.service.BaseServiceTipsMsgException;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pers.goetboy.entity.STATE_ENUM;
import pers.goetboy.entity.sys.Role;
import pers.goetboy.entity.sys.User;
import pers.goetboy.entity.sys.UserRole;
import pers.goetboy.mapper.RoleMapper;
import pers.goetboy.mapper.UserMapper;
import pers.goetboy.mapper.UserRoleMapper;

import java.util.List;

/**
 * @author goetb
 * @date 2018年12月6日 11点08分
 */
@Service("jwtUserDetail")
public class UserService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper roleUserMapper;
    @Autowired
    private RoleMapper roleMapper;

    public User get(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public Integer saveUser(User user) {
        return userMapper.insertSelective(user);
    }

    /**
     * 获取用户列表
     */
    public List<User> listUser() {
        List<User> users = userMapper.selectAll();
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        users.forEach(user -> {
            user.setRoles(roleMapper.selectByUserId(user.getId()));
        });
        return users;
    }

    /**
     * 更新用户信息和用户对应的角色信息
     *
     * @param user 用户信息
     */

    public void updateUser(User user) throws BaseServiceTipsMsgException {
        User oldUser = userMapper.selectByPrimaryKey(user.getId());
        if (oldUser == null) {
            throw new BaseServiceTipsMsgException("用户未找到");
        }
        oldUser.setNickName(user.getNickName());
        oldUser.setEmail(user.getEmail());
        oldUser.setAddress(user.getAddress());
        oldUser.setRemark(user.getRemark());
        oldUser.setState(user.getState());
        userMapper.updateByPrimaryKeySelective(oldUser);
    }

    /**
     * 更新用户角色信息
     *
     * @param userId 用户id
     * @param roles  角色信息
     */
    public void updateUserRole(Integer userId, List<Role> roles) {

        List<Role> oldRoles = roleMapper.selectByUserId(userId);
        //过滤已有角色信息
        if (CollectionUtils.isNotEmpty(oldRoles) && CollectionUtils.isNotEmpty(roles)) {
            roles.forEach(role -> {
                oldRoles.forEach(oldRole -> {
                    if (oldRole.getId().equals(role.getId())) {
                        oldRoles.remove(role);
                    }
                });
            });
        }
        //删除不在存在的角色信息
        if (CollectionUtils.isNotEmpty(oldRoles)) {
            oldRoles.forEach(role -> {
                roleUserMapper.deleteByUserIdAndRoleId(userId, role.getId());
            });
        }
        //添加新的角色信息
        if (CollectionUtils.isNotEmpty(roles)) {
            roles.forEach(role -> {
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(role.getId());
                userRole.setUserId(userId);
                roleUserMapper.insertSelective(userRole);
            });

        }

    }

    /**
     * 删除用户信息以及用户对应的角色映射信息
     *
     * @param id 用户id
     */
    public void deleteUser(Integer id) {
        //删除用户信息
        userMapper.deleteByPrimaryKey(id);
        //删除角色信息
        roleUserMapper.deleteByUserId(id);
    }

    public void updateUserState(Integer userId, Integer state) throws BaseServiceTipsMsgException {
        //如果传入状态不正确
        if (state == null || STATE_ENUM.getByValue(state) == null) {
            throw new BaseServiceTipsMsgException("用户状态不正确");
        }
        User user = userMapper.selectByPrimaryKey(userId);
        //如果用户没找到
        if (user == null) {
            throw new BaseServiceTipsMsgException("用户未找到");
        }
        //如果状态相等,不执行更新
        if (user.getState().equals(state)) {
            return;
        }
        user.setState(state);
        userMapper.updateByPrimaryKeySelective(user);
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

    public List<Role> loadUserRoles(Integer userId) {
        List<Role> roles = roleMapper.selectByUserId(userId);
        if (roles == null || roles.isEmpty()) {
            return null;
        } else {
            return roles;
        }

    }
}
