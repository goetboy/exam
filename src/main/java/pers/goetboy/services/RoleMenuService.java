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

    public RoleMenu get(Integer id) {
        return roleMenuMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<RoleMenu> findAll() {
        return roleMenuMapper.selectAll();
    }

    /**
     * 保存角色菜单映射信息
     *
     * @param roleMenu
     * @return
     */
    public Integer save(RoleMenu roleMenu) {
        Integer id = roleMenuMapper.insertSelective(roleMenu);
        return id;
    }

    /**
     * 更新角色菜单映射信息
     *
     * @param roleMenu
     */
    public void update(RoleMenu roleMenu) {
        roleMenuMapper.updateByPrimaryKeySelective(roleMenu);
    }

    /**
     * 删除角色菜单映射信息
     *
     * @param id
     */
    public void delete(Integer id) {
        roleMenuMapper.deleteByPrimaryKey(id);
    }


}
