package pers.goetboy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.goetboy.entity.sys.Role;
import pers.goetboy.mapper.RoleMapper;

import java.util.List;

@Service
public class RoleService extends AbstractService<Role> {
    @Autowired
    RoleMapper roleMapper;

    public Role get(Integer id) {
        return roleMapper.get(id);
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    List<Role> listAll() {
        return roleMapper.getAll();
    }

    @Override
    void save(Role user) {
        roleMapper.dynamicInsert(user);
    }

    @Override
    void update(Role user) {
        roleMapper.dynamicUpdate(user);
    }

    @Override
    void delete(Integer id) {
        roleMapper.delete(id);
    }


}
