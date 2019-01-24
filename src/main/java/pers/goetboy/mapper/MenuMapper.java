package pers.goetboy.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import pers.goetboy.entity.sys.Menu;
import pers.goetboy.entity.sys.RoleMenu;
import pers.goetboy.entity.sys.UserRole;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 菜单查询接口
 *
 * @author goetb
 */
@Repository
public interface MenuMapper extends Mapper<Menu> {
    /**
     * 通过角色id查询菜单权限
     *
     * @param roleId 角色id
     * @return 菜单列表
     */
    @Select("select sm.* from " + RoleMenu.TABLE_NAME + " srm," + Menu.TABLE_NAME + " sm where srm.menu_id=sm.id(+) and srm.role_id=#{roleId}")
    public List<Menu> selectByRoleId(Integer roleId);

    /**
     * 通过用户id查询菜单权限
     *
     * @param userId 用户id
     * @return 菜单列表
     */
    @Select("select sm.* from " + RoleMenu.TABLE_NAME + " srm," + UserRole.TABLE_NAME + " sur," + Menu.TABLE_NAME + " sm where srm.menu_id=sm.id(+) and  srm.role_id=sur.role_id and sur.user_id=#{userId}")
    public List<Menu> selectByUseId(Integer userId);
}
