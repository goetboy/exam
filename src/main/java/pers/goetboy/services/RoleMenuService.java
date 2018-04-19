package pers.goetboy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.goetboy.entity.sys.RoleMenu;
import pers.goetboy.mapper.RoleMenuMapper;

import java.util.List;

@Service
public class RoleMenuService extends AbstractService<RoleMenu> {
    @Autowired
    RoleMenuMapper roleMenuMapper;
    @Override
    public RoleMenu get(Integer id) {
        return roleMenuMapper.get(id);
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    List<RoleMenu> listAll() {
        return roleMenuMapper.getAll();
    }

    @Override
    void save(RoleMenu user) {
        roleMenuMapper.dynamicInsert(user);
    }

    @Override
    void update(RoleMenu user) {
        roleMenuMapper.dynamicUpdate(user);
    }

    @Override
    void delete(Integer id) {
        roleMenuMapper.delete(id);
    }


}
