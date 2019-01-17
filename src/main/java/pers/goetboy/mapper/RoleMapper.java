package pers.goetboy.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;
import pers.goetboy.entity.sys.Role;
import pers.goetboy.entity.sys.RoleMenu;
import pers.goetboy.entity.sys.UserRole;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface RoleMapper extends Mapper<Role> {


    @Select("select t.* from " + Role.TABLE_NAME + " t where t.id in (select t1.role_id from " + UserRole.TABLE_NAME + " t1 where  t1.user_id=#{userId}) ")
    List<Role> selectByUserId(Integer userId);

    /**
     * 通过菜单查角色
     *
     * @param menuId
     * @return
     */
    @Select("select t.* from " + Role.TABLE_NAME + " t where t.id in (select t1.role_id from " + RoleMenu.TABLE_NAME + " t1 where  t1.menu_id=#{menuId}) ")
    List<Role> selectByMenuId(Integer menuId);

}
