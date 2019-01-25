package pers.goetboy.services;

import com.goetboy.core.exception.service.BaseServiceTipsMsgException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.goetboy.entity.STATE_ENUM;
import pers.goetboy.entity.sys.Menu;
import pers.goetboy.mapper.MenuMapper;
import pers.goetboy.mapper.RoleMapper;
import pers.goetboy.mapper.RoleMenuMapper;

import java.util.List;

/**
 * 菜单业务层
 *
 * @author goetb
 */
@Service
public class MenuService extends AbstractService<Menu> {
    private final MenuMapper menuMapper;
    private final RoleMenuMapper roleMenuMapper;
    private final RoleMapper roleMapper;

    @Autowired
    public MenuService(MenuMapper menuMapper, RoleMenuMapper roleMenuMapper, RoleMapper roleMapper) {
        this.menuMapper = menuMapper;
        this.roleMenuMapper = roleMenuMapper;
        this.roleMapper = roleMapper;
    }

    public Menu get(Integer id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<Menu> listMenu() {
        return menuMapper.selectAll();
    }


    /**
     * 保存菜单
     *
     * @param menu
     * @return
     */
    public Integer saveMenu(Menu menu) {
        menu.setState(1);
        return menuMapper.insertSelective(menu);
    }

    /**
     * 更新菜单
     *
     * @param menu
     */
    public void updateMenu(Menu menu) throws BaseServiceTipsMsgException {
        Menu oldMenu = menuMapper.selectByPrimaryKey(menu.getId());
        if (oldMenu == null) {
            throw new BaseServiceTipsMsgException("没有找到菜单");
        }
        oldMenu.setUrl(menu.getUrl());
        oldMenu.setName(menu.getName());
        oldMenu.setParentId(menu.getParentId());
        oldMenu.setSort(menu.getSort());
        oldMenu.setType(menu.getType());
        menuMapper.updateByPrimaryKeySelective(oldMenu);

    }

    /**
     * 删除菜单以及菜单对应的角色菜单映射
     *
     * @param id
     */
    public void deleteMenu(Integer id) {
        roleMenuMapper.deleteByMenuId(id);
        menuMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新菜单状态
     */
    public void updateMenuState(Integer menuId, Integer state) throws BaseServiceTipsMsgException {
        //如果传入状态不正确
        if (state == null || STATE_ENUM.getByValue(state) == null) {
            throw new BaseServiceTipsMsgException("菜单状态不正确");
        }
        Menu oldMenu = menuMapper.selectByPrimaryKey(menuId);
        if (oldMenu == null) {
            throw new BaseServiceTipsMsgException("没有找到菜单");
        }
        //如果状态相等,不执行更新
        if (oldMenu.getState().equals(state)) {
            return;
        }
        oldMenu.setState(state);
        menuMapper.updateByPrimaryKeySelective(oldMenu);
    }
}
