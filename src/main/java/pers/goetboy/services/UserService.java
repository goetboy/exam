package pers.goetboy.services;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.goetboy.common.exception.service.ServiceTipsException;
import pers.goetboy.entity.STATE_ENUM;
import pers.goetboy.entity.sys.Role;
import pers.goetboy.entity.sys.User;
import pers.goetboy.entity.sys.UserRole;
import pers.goetboy.mapper.RoleMapper;
import pers.goetboy.mapper.UserMapper;
import pers.goetboy.mapper.UserRoleMapper;
import pers.goetboy.security.JWTUtil;
import pers.goetboy.utils.DataBus;

import java.util.*;

/**
 * @author goetb
 * @date 2018年12月6日 11点08分
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRoleMapper roleUserMapper;
    @Autowired
    RoleMapper roleMapper;

    public User get(Long id) {
        return userMapper.get(id);
    }

    /**
     * 获取用户列表
     */
    public List<User> listUser() {
        List<User> users = userMapper.getAll();
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        users.forEach(user -> {
            user.setRoles(roleMapper.findByUserId(user.getId()));
        });
        return users;
    }

    /**
     * 更新用户信息和用户对应的角色信息
     *
     * @param user 用户信息
     */

    public void updateUser(User user) throws ServiceTipsException {
        User oldUser = userMapper.get(user.getId());
        if (oldUser == null) {
            throw new ServiceTipsException("用户未找到");
        }
        oldUser.setRemark(user.getRemark());
        userMapper.dynamicUpdate(oldUser);
    }

    /**
     * 更新用户角色信息
     *
     * @param userId 用户id
     * @param roles  角色信息
     */
    public void updateUserRole(long userId, List<Role> roles) {

        List<Role> oldRoles = roleMapper.findByUserId(userId);
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
            UserRole roleUser = new UserRole();
            roleUser.setRoleId(roles.get(0).getId());
            roleUser.setUserId(userId);
            roleUserMapper.dynamicInsert(roleUser);
        }

    }

    /**
     * 删除用户信息以及用户对应的角色映射信息
     *
     * @param id 用户id
     */
    public void deleteUser(Long id) {
        //删除用户信息
        userMapper.delete(id);
        //删除角色信息
        roleUserMapper.deleteByUserId(id);
    }

    public void updateUserState(Long userId, Integer state) throws ServiceTipsException {
        //如果传入状态不正确
        if (state == null || STATE_ENUM.getByValue(state) == null) {
            throw new ServiceTipsException("用户状态不正确");
        }
        User user = userMapper.get(userId);
        //如果用户没找到
        if (user == null) {
            throw new ServiceTipsException("用户未找到");
        }
        //如果状态相等,不执行更新
        if (user.getState().equals(state)) {
            return;
        }
        user.setState(state);
        userMapper.dynamicUpdate(user);
    }

}
