package pers.goetboy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pers.goetboy.common.exception.service.ServiceTipsException;
import pers.goetboy.entity.sys.Role;
import pers.goetboy.entity.sys.RoleUser;
import pers.goetboy.entity.sys.User;
import pers.goetboy.mapper.RoleUserMapper;
import pers.goetboy.mapper.UserMapper;

import java.util.List;

@Service
public class UserService extends AbstractService<User> {
    @Autowired
    UserMapper userMapper;
    RoleUserMapper roleUserMapper;


    @Override
    public User get(Long id) {
        return userMapper.get(id);
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<User> findAll() {
        return userMapper.getAll();
    }

    /**
     * 保存用户信息和用户对应的角色信息
     *
     * @param user
     */
    @Override
    public Long save(User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        Long id = userMapper.dynamicInsert(user);

        List<Role> roles = user.getRoles();
        if (roles != null) {
            RoleUser roleUser = new RoleUser();
            roleUser.setRoleId(roles.get(0).getId());
            roleUser.setUserId(id);
            roleUserMapper.dynamicInsert(roleUser);
        }

        return id;
    }

    /**
     * 更新用户信息和用户对应的角色信息
     *
     * @param user 用户信息
     */
    @Override
    public void update(User user) {
        userMapper.dynamicUpdate(user);
        List<Role> roles = user.getRoles();
        if (roles != null) {
            RoleUser roleUser = new RoleUser();
            roleUser.setRoleId(roles.get(0).getId());
            roleUser.setUserId(user.getId());
            roleUserMapper.dynamicInsert(roleUser);
        }

    }

    /**
     * 更新用户密码
     *
     * @param id       用户id
     * @param password 密码
     * @throws ServiceTipsException
     */
    public void updatePassword(Long id, String password) throws ServiceTipsException {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = userMapper.get(id);
        if (user == null) {
            throw new ServiceTipsException("找不到对应的用户信息");
        }

        user.setPassword(bCryptPasswordEncoder.encode(password));

        userMapper.dynamicUpdate(user);

    }

    /**
     * 删除用户信息以及用户对应的角色映射信息
     *
     * @param id 用户id
     */
    @Override
    public void delete(Long id) {
        userMapper.delete(id);
        roleUserMapper.deleteByUserId(id);
    }

}
