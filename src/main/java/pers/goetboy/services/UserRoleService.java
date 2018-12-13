package pers.goetboy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.goetboy.entity.sys.UserRole;
import pers.goetboy.mapper.UserRoleMapper;

import java.util.List;

@Service
public class UserRoleService extends AbstractService<UserRole> {
    @Autowired
    UserRoleMapper userRoleMapper;
    public UserRole get(Long id) {
        return userRoleMapper.get(id);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public  List<UserRole> findAll() {
        return userRoleMapper.getAll();
    }

    /**
     * 保存用户角色映射信息
     * @param UserRole
     * @return
     */
    public  Long save(UserRole UserRole) {
        Long id = userRoleMapper.dynamicInsert(UserRole);
        return  id;
    }
    /**
     * 更新用户角色映射信息
     * @param UserRole
     * @return
     */
    public  void update(UserRole UserRole) {
        userRoleMapper.dynamicUpdate(UserRole);
    }

    /**
     * 删除角色菜单映射信息
     * @param id
     */
    public  void delete(Long id) {
        userRoleMapper.delete(id);
    }


}
