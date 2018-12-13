package pers.goetboy.services;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.goetboy.entity.sys.Menu;
import pers.goetboy.mapper.MenuMapper;
import pers.goetboy.mapper.RoleMapper;
import pers.goetboy.mapper.RoleMenuMapper;

import java.util.List;

/**
 * 菜单业务层
 */
@Service
    public class MenuService extends AbstractService<Menu> {
    @Autowired
    MenuMapper menuMapper;
    @Autowired
    RoleMenuMapper roleMenuMapper;
    @Autowired
    RoleMapper roleMapper;

    public Menu get(Long id) {
        return menuMapper.get(id);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<Menu> findAll() {
        List<Menu> menus = menuMapper.getAll();
        for (Menu menu : menus) {
            menu.setRoles(roleMapper.findByMenuId(menu.getId()));
        }

        return menus;
    }


    /**
     * 保存菜单
     *
     * @param menu
     * @return
     */
    public Long save(Menu menu) {

        Long id = menuMapper.dynamicInsert(menu);
        return id;
    }

    /**
     * 更新菜单
     *
     * @param menu
     */
    public void update(Menu menu) {
        menuMapper.dynamicUpdate(menu);

    }

    /**
     * 删除菜单以及菜单对应的角色菜单映射
     *
     * @param id
     */
    public void delete(Long id) {
        menuMapper.delete(id);
        roleMenuMapper.deleteByMenuId(id);
    }

    /**
     * 通过用户名获取菜单权限
     *
     * @param userName
     * @return
     */
    public List<Menu> findByUserName(String userName) {
        return menuMapper.listByUserName(userName);
    }

}
