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
    public RoleMenu get(Long id) {
        return roleMenuMapper.get(id);
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public  List<RoleMenu> findAll() {
        return roleMenuMapper.getAll();
    }

    /**
     * 保存角色菜单映射信息
     * @param roleMenu
     * @return
     */
    @Override
    public  Long save(RoleMenu roleMenu) {
        Long id = roleMenuMapper.dynamicInsert(roleMenu);
        return id;
    }

    /**
     * 更新角色菜单映射信息
     * @param roleMenu
     */
    @Override
    public   void update(RoleMenu roleMenu) {
        roleMenuMapper.dynamicUpdate(roleMenu);
    }

    /**
     * 删除角色菜单映射信息
     * @param id
     */
    @Override
    public  void delete(Long id) {
        roleMenuMapper.delete(id);
    }


}
