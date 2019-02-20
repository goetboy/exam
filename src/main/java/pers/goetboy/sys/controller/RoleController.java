package pers.goetboy.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.goetboy.common.AbstractParam;
import pers.goetboy.common.exception.service.ServiceTipsException;
import pers.goetboy.common.EntityState;
import pers.goetboy.sys.model.entity.Menu;
import pers.goetboy.sys.model.entity.Role;
import pers.goetboy.sys.services.RoleService;

import java.util.List;

/**
 * @author goetb
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    private final
    RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 获取角色列表
     */
    @GetMapping("/list")
    public IPage<Role> list(Integer current, Integer size) {
        return roleService.page(new Page(current, size));
    }

    /**
     * 获取角色信息
     *
     * @param roleId 角色信息
     * @return 角色信息
     */
    @GetMapping(value = "/get")
    public Role get(Long roleId) {
        return roleService.get(roleId);
    }

    /**
     * 更新角色信息
     * 可更新 name 字段
     * 可更新 remark字段
     *
     * @param param 用户信息
     */
    @PostMapping(value = "/update")
    public void update(@RequestBody RoleParam param) throws ServiceTipsException {
        roleService.update(param.getEntity());
    }

    /**
     * 添加角色信息
     *
     * @param param 角色信息
     */
    @PostMapping(value = "/save")
    public void save(@RequestBody RoleParam param) throws ServiceTipsException {
        roleService.save(param.getEntity());
    }

    /**
     * 删除角色信息
     *
     * @param param 用户id
     */
    @PostMapping(value = "/delete")
    public void delete(@RequestBody RoleParam param) {
        roleService.delete(param.getId());
    }

    /**
     * 更新角色菜单信息
     *
     * @param param 角色信息/用户信息
     */
    @PostMapping(value = "/update/menu")
    public void updateRoleMenu(@RequestBody RoleParam param) throws ServiceTipsException {
        roleService.updateRoleMenu(param.getId(), param.getMenus());
    }

    /**
     * 更新角色状态
     *
     * @param param roleId, 角色状态 0停用 1正常
     * @throws ServiceTipsException 业务异常
     */
    @PostMapping(value = "/update/state")
    public void updateRoleState(@RequestBody RoleParam param) throws ServiceTipsException {
        roleService.updateState(param.getId(), EntityState.getByValue(param.getState()));
    }


}

@Data
@EqualsAndHashCode(callSuper = true)
class RoleParam extends AbstractParam<Role> {
    /**
     * 角色信息
     */
    private List<Menu> menus;

}