package pers.goetboy.services;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.goetboy.common.exception.service.ServiceTipsException;
import pers.goetboy.entity.STATE_ENUM;
import pers.goetboy.entity.sys.Menu;
import pers.goetboy.entity.sys.Role;
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
	public List<Menu> listMenu() {
		return menuMapper.getAll();
	}


	/**
	 * 保存菜单
	 *
	 * @param menu
	 * @return
	 */
	public Long saveMenu(Menu menu) {

		Long id = menuMapper.dynamicInsert(menu);
		return id;
	}

	/**
	 * 更新菜单
	 *
	 * @param menu
	 */
	public void updateMenu(Menu menu) throws ServiceTipsException {
		Menu oldMenu = menuMapper.get(menu.getId());
		if (oldMenu == null) {
			throw new ServiceTipsException("没有找到菜单");
		}
		oldMenu.setUrl(menu.getUrl());
		oldMenu.setName(menu.getName());
		oldMenu.setParent(menu.getParent());
		oldMenu.setSort(menu.getParent());
		oldMenu.setType(menu.getType());
		menuMapper.dynamicUpdate(oldMenu);

	}

	/**
	 * 删除菜单以及菜单对应的角色菜单映射
	 *
	 * @param id
	 */
	public void deleteMenu(Long id) {
		roleMenuMapper.deleteByMenuId(id);
		menuMapper.delete(id);
	}

	/**
	 * 更新菜单状态
	 */
	public void updateMenuState(Long menuId, Integer state) throws ServiceTipsException {
		//如果传入状态不正确
		if (state == null || STATE_ENUM.getByValue(state) == null) {
			throw new ServiceTipsException("菜单状态不正确");
		}
		Menu oldMenu = menuMapper.get(menuId);
		if (oldMenu == null) {
			throw new ServiceTipsException("没有找到菜单");
		}
		//如果状态相等,不执行更新
		if (oldMenu.getState().equals(state)) {
			return;
		}
		oldMenu.setState(state);
		menuMapper.dynamicUpdate(oldMenu);
	}
}
