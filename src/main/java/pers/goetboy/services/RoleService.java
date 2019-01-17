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
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class RoleService extends AbstractService<Role> {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    RoleMenuMapper roleMenuMapper;

    public Role get(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询角色列表
     *
     * @return
     */

    public List<Role> listRole() {
        return roleMapper.selectAll();
    }

    /**
     * 保存角色信息
     *
     * @param role
     * @return
     * @throws BaseServiceTipsMsgException
     */

    public Integer saveRole(Role role) throws BaseServiceTipsMsgException {
        Example example = new Example(Role.class);
        example.createCriteria().andEqualTo("name", role.getName());
        Role existRole = roleMapper.selectOneByExample(example);
        if (existRole != null) {
            throw new BaseServiceTipsMsgException("已经有同名角色存在");
        }
        role.setState(1);
        Integer id = roleMapper.insertSelective(role);
        List<Menu> menus = role.getMenus();
        if (menus != null && !menus.isEmpty()) {
            for (Menu menu : menus) {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setMenuId(menu.getId());
                roleMenu.setRoleId(id);
                roleMenuMapper.insertSelective(roleMenu);
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
        Role oldRole = roleMapper.selectByPrimaryKey(role.getId());
        if (oldRole == null) {
            throw new BaseServiceTipsMsgException("没有找到角色");
        }
        Example example = new Example(Role.class);
        example.createCriteria().andEqualTo("name", role.getName());
        Role existRole = roleMapper.selectOneByExample(example);
        if (existRole != null && !existRole.getId().equals(role.getId())) {
            throw new BaseServiceTipsMsgException("已经有同名角色存在");
        }
        oldRole.setName(role.getName());
        oldRole.setRemark(role.getRemark());
        roleMapper.updateByPrimaryKeySelective(oldRole);
    }

    /**
     * 更新角色菜单
     */
    public void updateRoleMenu(Integer roleId, List<Menu> menus) throws BaseServiceTipsMsgException {
        Role oldRole = roleMapper.selectByPrimaryKey(roleId);
        if (oldRole == null) {
            throw new BaseServiceTipsMsgException("没有找到角色");
        }
        roleMenuMapper.deleteByRoleId(roleId);
        if (CollectionUtils.isNotEmpty(menus)) {
            menus.forEach(menu -> {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setMenuId(menu.getId());
                roleMenu.setRoleId(roleId);
                roleMenuMapper.insertSelective(roleMenu);
            });

        }
    }

    /**
     * 更新角色状态
     */
    public void updateRoleState(Integer roleId, Integer state) throws BaseServiceTipsMsgException {
        //如果传入状态不正确
        if (state == null || STATE_ENUM.getByValue(state) == null) {
            throw new BaseServiceTipsMsgException("角色状态不正确");
        }
        Role oldRole = roleMapper.selectByPrimaryKey(roleId);
        if (oldRole == null) {
            throw new BaseServiceTipsMsgException("没有找到角色");
        }
        //如果状态相等,不执行更新
        if (oldRole.getState().equals(state)) {
            return;
        }
        oldRole.setState(state);
        roleMapper.updateByPrimaryKeySelective(oldRole);
    }

    /**
     * 删除角色以及角色对应的用户角色映射和角色菜单映射
     *
     * @param id
     */

    public void deleteRole(Integer id) {
        //删除角色和菜单映射关系
        roleMenuMapper.deleteByRoleId(id);
        //删除用户和角色映射关系
        userRoleMapper.deleteByRoleId(id);
        //删除角色
        roleMapper.deleteByPrimaryKey(id);
    }

    public List<Role> listRoleByUser(Integer userId) {
        return roleMapper.selectByUserId(userId);
    }
}
