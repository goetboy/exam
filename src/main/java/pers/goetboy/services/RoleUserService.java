package pers.goetboy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.goetboy.entity.sys.RoleUser;
import pers.goetboy.mapper.RoleUserMapper;

import java.util.List;

@Service
public class RoleUserService extends AbstractService<RoleUser> {
    @Autowired
    RoleUserMapper roleUserMapper;
    @Override
    public RoleUser get(Long id) {
        return roleUserMapper.get(id);
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public  List<RoleUser> findAll() {
        return roleUserMapper.getAll();
    }

    /**
     * 保存用户角色映射信息
     * @param roleUser
     * @return
     */
    @Override
    public  Long save(RoleUser roleUser) {
        Long id = roleUserMapper.dynamicInsert(roleUser);
        return  id;
    }
    /**
     * 更新用户角色映射信息
     * @param roleUser
     * @return
     */
    @Override
    public  void update(RoleUser roleUser) {
        roleUserMapper.dynamicUpdate(roleUser);
    }

    /**
     * 删除角色菜单映射信息
     * @param id
     */
    @Override
    public  void delete(Long id) {
        roleUserMapper.delete(id);
    }


}
