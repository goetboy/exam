package pers.goetboy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.goetboy.common.exception.service.ServiceTipsException;
import pers.goetboy.entity.sys.Menu;
import pers.goetboy.entity.sys.Role;
import pers.goetboy.entity.sys.RoleMenu;
import pers.goetboy.mapper.RoleMapper;
import pers.goetboy.mapper.RoleMenuMapper;
import pers.goetboy.mapper.RoleUserMapper;

import java.util.List;

@Service
public class RoleService extends AbstractService<Role> {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    RoleUserMapper roleUserMapper;
    @Autowired
    RoleMenuMapper roleMenuMapper;

    public Role get(Long id) {
        return roleMapper.get(id);
    }

    /**
     * 查询所有
     *
     * @return
     */

    public List<Role> findAll() {
        return roleMapper.getAll();
    }

    /**
     * 保存角色信息
     *
     * @param role
     * @return
     * @throws ServiceTipsException
     */

    public Long save(Role role) throws ServiceTipsException {
        Role o_role = roleMapper.getByName(role.getName());
        if (o_role != null) {
            throw new ServiceTipsException("已经有同名角色存在");
        }
        Long id = roleMapper.dynamicInsert(role);
        List<Menu> menus = role.getMenus();
        if (menus != null && !menus.isEmpty()) {
            for (Menu menu : menus) {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setMenuId(menu.getId());
                roleMenu.setRoleId(id);
                roleMenuMapper.dynamicInsert(roleMenu);
            }
        }
        return id;
    }

    /**
     * 更新角色信息
     *
     * @param role
     */

    public void update(Role role) {
        roleMenuMapper.deleteByRoleId(role.getId());
        roleMapper.dynamicUpdate(role);
        List<Menu> menus = role.getMenus();

        if (menus != null && !menus.isEmpty()) {
            for (Menu menu : menus) {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setMenuId(menu.getId());
                roleMenu.setRoleId(role.getId());
                roleMenuMapper.dynamicInsert(roleMenu);
            }

        }

    }

    /**
     * 查询用户下所有角色
     *
     * @param userId
     * @return
     */
    public List<Role> listRoleByUser(Long userId) {
        return roleMapper.findByUserId(userId);
    }

    /**
     * 删除角色以及角色对应的用户角色映射和角色菜单映射
     *
     * @param id
     */

    public void delete(Long id) {
        roleMapper.delete(id);
        roleUserMapper.deleteByRoleId(id);
        roleMenuMapper.deleteByRoleId(id);
    }


}
