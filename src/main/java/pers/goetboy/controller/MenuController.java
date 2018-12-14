package pers.goetboy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pers.goetboy.common.exception.service.ServiceTipsException;
import pers.goetboy.entity.sys.Menu;
import pers.goetboy.services.MenuService;

import java.util.List;
@RestController
@RequestMapping("/menu")
public class MenuController extends AbstractController {
    @Autowired
    MenuService menuService;

	/**
	 * 获取用户列表
	 */
	@GetMapping("/list")
	public List<Menu> list() {
		return menuService.listMenu();
	}

	/**
	 * 获取用户信息
	 *
	 * @param menuId
	 * @return
	 */
	@GetMapping(value = "/get")
	public Menu get(Long menuId) {
		return menuService.get(menuId);
	}

	/**
	 * 更新菜单信息
	 * 可更新 nickName 字段
	 * 可更新 remark字段
	 *
	 * @param menu 菜单信息
	 */
	@PostMapping(value = "/update")
	public void update(Menu menu) throws ServiceTipsException {
		menuService.updateMenu(menu);
	}

	/**
	 * 删除菜单信息
	 *
	 * @param menuId 菜单id
	 */
	@PostMapping(value = "/delete")
	public void delete(Long menuId) {
		menuService.deleteMenu(menuId);
	}

	/**
	 * 更新菜单状态
	 *
	 * @param menuId 用户id
	 * @param state  菜单状态 0停用 1正常
	 * @throws ServiceTipsException
	 * 异常信息
	 */
	@PostMapping(value = "/update/state")
	public void updateUserState(Long menuId, Integer state) throws ServiceTipsException {
		menuService.updateMenuState(menuId, state);
	}
}
