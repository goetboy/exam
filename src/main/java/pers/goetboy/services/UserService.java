package pers.goetboy.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.goetboy.common.AbstractService;
import pers.goetboy.common.exception.service.ServiceTipsException;
import pers.goetboy.entity.sys.Role;
import pers.goetboy.entity.sys.User;
import pers.goetboy.entity.sys.UserRole;
import pers.goetboy.mapper.RoleMapper;
import pers.goetboy.mapper.UserMapper;
import pers.goetboy.mapper.UserRoleMapper;

import java.util.List;

/**
 * 用户业务类
 *
 * @author goetb
 * @date 2018年12月6日 11点08分
 */
@Service
public class UserService extends AbstractService<User> {
    private final UserMapper userMapper;
    private final UserRoleMapper roleUserMapper;
    private final RoleMapper roleMapper;

    @Autowired
    public UserService(UserMapper userMapper, UserRoleMapper roleUserMapper, RoleMapper roleMapper) {
        super(userMapper);
        this.userMapper = userMapper;
        this.roleUserMapper = roleUserMapper;
        this.roleMapper = roleMapper;
    }


    /**
     * 获取用户列表
     */
    @Override
    public IPage<User> page(IPage page) {
        IPage<User> result = baseMapper.selectPage(page, null);
        if (CollectionUtils.isEmpty(result.getRecords())) {
            return null;
        }
        result.getRecords().forEach(user -> user.setRoles(roleMapper.selectByUserId(user.getId())));
        return result;
    }


    /**
     * 更新用户信息和用户对应的角色信息
     *
     * @param user 用户信息
     */

    @Override
    public void update(User user) throws ServiceTipsException {
        User oldUser = userMapper.selectById(user.getId());
        if (oldUser == null) {
            throw new ServiceTipsException("用户未找到");
        }
        oldUser.setNickName(user.getNickName());
        oldUser.setEmail(user.getEmail());
        oldUser.setAddress(user.getAddress());
        oldUser.setRemark(user.getRemark());
        oldUser.setState(user.getState());
        userMapper.updateById(oldUser);
    }

    /**
     * 更新用户角色信息
     *
     * @param userId 用户id
     * @param roles  角色信息
     */
    public void updateUserRole(Long userId, List<Role> roles) {

        List<Role> oldRoles = roleMapper.selectByUserId(userId);
        //过滤已有角色信息
        if (CollectionUtils.isNotEmpty(oldRoles) && CollectionUtils.isNotEmpty(roles)) {
            roles.forEach(role -> oldRoles.forEach(oldRole -> {
                if (oldRole.getId().equals(role.getId())) {
                    oldRoles.remove(role);
                }
            }));
        }
        //删除不在存在的角色信息
        if (CollectionUtils.isNotEmpty(oldRoles)) {
            oldRoles.forEach(role -> roleUserMapper.deleteByUserIdAndRoleId(userId, role.getId()));
        }
        //添加新的角色信息
        if (CollectionUtils.isNotEmpty(roles)) {
            roles.forEach(role -> {
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(role.getId());
                userRole.setUserId(userId);
                roleUserMapper.insert(userRole);
            });

        }

    }

    /**
     * 删除用户信息以及用户对应的角色映射信息
     *
     * @param id 用户id
     */
    @Override
    public void delete(Long id) {
        //删除用户信息
        userMapper.deleteById(id);
        //删除角色信息
        roleUserMapper.deleteByUserId(id);
    }


}
