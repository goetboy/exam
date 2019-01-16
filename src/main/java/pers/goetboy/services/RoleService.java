package pers.goetboy.services;

import com.goetboy.core.exception.service.BaseServiceTipsMsgException;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.goetboy.entity.STATE_ENUM;
import pers.goetboy.entity.sys.Menu;
import pers.goetboy.entity.sys.Role;
import pers.goetboy.entity.sys.RoleMenu;
import pers.goetboy.mapper.RoleMapper;
import pers.goetboy.mapper.RoleMenuMapper;
import pers.goetboy.mapper.UserRoleMapper;

import java.util.List;

@Service
public class RoleService extends AbstractService<Role> {
	@Autowired
	RoleMapper roleMapper;
	@Autowired
	UserRoleMapper userRoleMapper;
	@Autowired
	RoleMenuMapper roleMenuMapper;

	public Role get(Long id) {
		return roleMapper.get(id);
	}

	/**
	 * 查询角色列表
	 *
	 * @return
	 */

	public List<Role> listRole() {
		return roleMapper.getAll();
	}

	/**
	 * 保存角色信息
	 *
	 * @param role
	 * @return
	 * @throws BaseServiceTipsMsgException
	 */

	public Long saveRole(Role role) throws BaseServiceTipsMsgException {
		Role existRole = roleMapper.getByName(role.getName());
		if (existRole != null) {
			throw new BaseServiceTipsMsgException("已经有同名角色存在");
		}
		role.setState(1);
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

	public void updateRole(Role role) throws BaseServiceTipsMsgException {
		Role oldRole = roleMapper.get(role.getId());
		if (oldRole == null) {
			throw new BaseServiceTipsMsgException("没有找到角色");
		}
		Role existRole = roleMapper.getByName(role.getName());
		if (existRole != null&& !existRole.getId().equals(role.getId())) {
			throw new BaseServiceTipsMsgException("已经有同名角色存在");
		}
		oldRole.setName(role.getName());
		oldRole.setRemark(role.getRemark());
		roleMapper.dynamicUpdate(oldRole);
	}

	/**
	 * 更新角色菜单
	 */
	public void updateRoleMenu(Long roleId, List<Menu> menus) throws BaseServiceTipsMsgException {
		Role oldRole = roleMapper.get(roleId);
		if (oldRole == null) {
			throw new BaseServiceTipsMsgException("没有找到角色");
		}
		roleMenuMapper.deleteByRoleId(roleId);
		if (CollectionUtils.isNotEmpty(menus)) {
			menus.forEach(menu -> {
				RoleMenu roleMenu = new RoleMenu();
				roleMenu.setMenuId(menu.getId());
				roleMenu.setRoleId(roleId);
				roleMenuMapper.dynamicInsert(roleMenu);
			});

		}
	}

	/**
	 * 更新角色状态
	 */
	public void updateRoleState(Long roleId, Integer state) throws BaseServiceTipsMsgException {
		//如果传入状态不正确
		if (state == null || STATE_ENUM.getByValue(state) == null) {
			throw new BaseServiceTipsMsgException("角色状态不正确");
		}
		Role oldRole = roleMapper.get(roleId);
		if (oldRole == null) {
			throw new BaseServiceTipsMsgException("没有找到角色");
		}
		//如果状态相等,不执行更新
		if (oldRole.getState().equals(state)) {
			return;
		}
		oldRole.setState(state);
		roleMapper.dynamicUpdate(oldRole);
	}

	/**
	 * 删除角色以及角色对应的用户角色映射和角色菜单映射
	 *
	 * @param id
	 */

	public void deleteRole(Long id) {
		//删除角色和菜单映射关系
		roleMenuMapper.deleteByRoleId(id);
		//删除用户和角色映射关系
		userRoleMapper.deleteByRoleId(id);
		//删除角色
		roleMapper.delete(id);
	}

	public List<Role> listRoleByUser(Long userId) {
		return roleMapper.findByUserId(userId);
	}
}
