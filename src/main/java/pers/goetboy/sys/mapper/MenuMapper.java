package pers.goetboy.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import pers.goetboy.sys.model.entity.Menu;
import pers.goetboy.sys.model.entity.RoleMenu;
import pers.goetboy.sys.model.entity.UserRole;

import java.util.List;

/**
 * 菜单查询接口
 *
 * @author goetb
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 通过角色id查询菜单权限
     *
     * @param roleId 角色id
     * @return 菜单列表
     */
    @Select("select sm.* from " + RoleMenu.TABLE_NAME + " srm," + Menu.TABLE_NAME + " sm where srm.menu_id=sm.id(+) and srm.role_id=#{roleId}")
    List<Menu> selectByRoleId(Long roleId);

    /**
     * 通过用户id查询菜单权限
     *
     * @param userId 用户id
     * @return 菜单列表
     */
    @Select("select sm.* from " + RoleMenu.TABLE_NAME + " srm," + UserRole.TABLE_NAME + " sur," + Menu.TABLE_NAME + " sm where srm.menu_id=sm.id(+) and  srm.role_id=sur.role_id and sur.user_id=#{userId}")
    List<Menu> selectByUseId(Long userId);
}
