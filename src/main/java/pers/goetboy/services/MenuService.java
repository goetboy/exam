package pers.goetboy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.goetboy.common.AbstractService;
import pers.goetboy.common.exception.service.ServiceTipsException;
import pers.goetboy.entity.STATE_ENUM;
import pers.goetboy.entity.sys.Menu;
import pers.goetboy.mapper.MenuMapper;
import pers.goetboy.mapper.RoleMenuMapper;

/**
 * 菜单业务层
 *
 * @author goetb
 */
@Service
public class MenuService extends AbstractService<Menu> {
    private final MenuMapper menuMapper;
    private final RoleMenuMapper roleMenuMapper;

    @Autowired
    public MenuService(MenuMapper menuMapper, RoleMenuMapper roleMenuMapper) {
        this.menuMapper = menuMapper;
        super.baseMapper = menuMapper;
        this.roleMenuMapper = roleMenuMapper;

    }

    /**
     * 更新菜单
     *
     * @param menu 菜单信息
     */
    @Override
    public void update(Menu menu) throws ServiceTipsException {
        Menu oldMenu = menuMapper.selectById(menu.getId());
        if (oldMenu == null) {
            throw new ServiceTipsException("没有找到菜单");
        }
        oldMenu.setUrl(menu.getUrl());
        oldMenu.setName(menu.getName());
        oldMenu.setParentId(menu.getParentId());
        oldMenu.setSort(menu.getSort());
        oldMenu.setType(menu.getType());
        menuMapper.updateById(oldMenu);

    }

    /**
     * 删除菜单以及菜单对应的角色菜单映射
     *
     * @param id 菜单id
     */
    @Override
    public void delete(Long id) {
        roleMenuMapper.deleteByMenuId(id);
        menuMapper.deleteById(id);
    }


}
