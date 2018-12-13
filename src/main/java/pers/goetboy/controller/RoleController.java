package pers.goetboy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.goetboy.common.exception.service.ServiceTipsException;
import pers.goetboy.entity.sys.Menu;
import pers.goetboy.entity.sys.Role;
import pers.goetboy.entity.sys.Role;
import pers.goetboy.services.RoleService;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    /**
     * 获取角色列表
     */
    @GetMapping("/list")
    public List<Role> list() {
        return roleService.listRole();
    }

    /**
     * 获取角色信息
     *
     * @param roleId 角色信息
     * @return
     */
    @GetMapping(value = "/get")
    public Role get(Long roleId) {
        return roleService.get(roleId);
    }

    /**
     * 更新用户信息
     * 可更新 name 字段
     * 可更新 remark字段
     * @param role 用户信息
     */
    @PostMapping(value = "/update")
    public void update(Role role) throws ServiceTipsException {
        roleService.updateRole(role);
    }

    /**
     * 删除用户信息
     *
     * @param roleId 用户id
     */
    @PostMapping(value = "/delete")
    public void delete(Long roleId) {
        roleService.deleteRole(roleId);
    }

    /**
     * 更新用户角色信息
     *
     * @param roleId 用户id
     * @param menus  角色信息
     */
    @PostMapping(value = "/update/menu")
    public void updateRoleMenu(Long roleId, List<Menu> menus) throws ServiceTipsException {
        roleService.updateRoleMenu(roleId, menus);
    }

    /**
     * 更新用户状态
     *
     * @param RoleId 用户id
     * @param state  用户状态 0停用 1正常
     * @throws ServiceTipsException
     */
    @PostMapping(value = "/update/state")
    public void updateRoleState(Long RoleId, Integer state) throws ServiceTipsException {
        roleService.updateRoleState(RoleId, state);
    }
}
