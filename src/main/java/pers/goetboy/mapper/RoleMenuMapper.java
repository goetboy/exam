package pers.goetboy.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;
import pers.goetboy.entity.sys.Menu;
import pers.goetboy.entity.sys.Role;
import pers.goetboy.entity.sys.RoleMenu;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface RoleMenuMapper extends Mapper<RoleMenu> {

    /**
     * 删除角色对应的菜单关系
     * @param roleId
     */
    @Delete("delete " + RoleMenu.TABLE_NAME + " where role_id =#{roleId} ")
    void deleteByRoleId(Integer roleId);
    @Delete("delete " + RoleMenu.TABLE_NAME + " where menu_id =#{menuId} ")
    void deleteByMenuId(Integer menuId);

}
