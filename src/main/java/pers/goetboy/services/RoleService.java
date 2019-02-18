package pers.goetboy.services;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.goetboy.common.AbstractService;
import pers.goetboy.common.exception.service.ServiceTipsException;
import pers.goetboy.entity.sys.Menu;
import pers.goetboy.entity.sys.Role;
import pers.goetboy.entity.sys.RoleMenu;
import pers.goetboy.mapper.RoleMapper;
import pers.goetboy.mapper.RoleMenuMapper;
import pers.goetboy.mapper.UserRoleMapper;

import java.util.List;

/**
 * @author goetb
 */
@Service
public class RoleService extends AbstractService<Role> {
    private final
    RoleMapper roleMapper;
    private final
    UserRoleMapper userRoleMapper;
    private final
    RoleMenuMapper roleMenuMapper;

    @Autowired
    public RoleService(RoleMapper roleMapper, UserRoleMapper userRoleMapper, RoleMenuMapper roleMenuMapper) {
        super(roleMapper);
        this.roleMapper = roleMapper;
        this.userRoleMapper = userRoleMapper;
        this.roleMenuMapper = roleMenuMapper;
    }


    /**
     * 保存角色信息
     *
     * @param role 角色信息
     * @return id 角色id
     * @throws ServiceTipsException 异常
     */

    @Override
    public Long save(Role role) throws ServiceTipsException {
        Role existRole = roleMapper.selectOne(Wrappers.<Role>lambdaQuery().eq(Role::getName, role.getName()));
        if (existRole != null) {
            throw new ServiceTipsException("已经有同名角色存在");
        }
        role.setState(1);
        roleMapper.insert(role);
        List<Menu> menus = role.getMenus();
        if (menus != null && !menus.isEmpty()) {
            for (Menu menu : menus) {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setMenuId(menu.getId());
                roleMenu.setRoleId(role.getId());
                roleMenuMapper.insert(roleMenu);
            }
        }
        return role.getId();
    }

    /**
     * 更新角色信息
     *
     * @param role 角色
     */

    @Override
    public void update(Role role) throws ServiceTipsException {
        Role oldRole = roleMapper.selectById(role.getId());
        if (oldRole == null) {
            throw new ServiceTipsException("没有找到角色");
        }

        Role existRole = roleMapper.selectOne(Wrappers.<Role>lambdaQuery().eq(Role::getName, role.getName()));
        if (existRole != null && !existRole.getId().equals(role.getId())) {
            throw new ServiceTipsException("已经有同名角色存在");
        }
        oldRole.setName(role.getName());
        oldRole.setRemark(role.getRemark());

        roleMapper.updateById(oldRole);
    }

    /**
     * 更新角色菜单
     *
     * @param roleId 角色id
     * @param menus  菜单列表
     */
    public void updateRoleMenu(Long roleId, List<Menu> menus) throws ServiceTipsException {
        Role oldRole = roleMapper.selectById(roleId);
        if (oldRole == null) {
            throw new ServiceTipsException("没有找到角色");
        }
        roleMenuMapper.deleteByRoleId(roleId);
        if (CollectionUtils.isNotEmpty(menus)) {
            menus.forEach(menu -> {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setMenuId(menu.getId());
                roleMenu.setRoleId(roleId);
                roleMenuMapper.insert(roleMenu);
            });

        }
    }


    /**
     * 删除角色以及角色对应的用户角色映射和角色菜单映射
     *
     * @param id 角色id
     */

    @Override
    public void delete(Long id) {
        //删除角色和菜单映射关系
        roleMenuMapper.deleteByRoleId(id);
        //删除用户和角色映射关系
        userRoleMapper.deleteByRoleId(id);
        //删除角色
        roleMapper.deleteById(id);
    }

    /**
     * 通过用户id查询角色信息
     *
     * @param userId 用户id
     * @return 用户拥有角色列表
     */
    public List<Role> listRoleByUser(Long userId) {
        return roleMapper.selectByUserId(userId);
    }
}
