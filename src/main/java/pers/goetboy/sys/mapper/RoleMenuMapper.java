package pers.goetboy.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Repository;
import pers.goetboy.sys.model.entity.RoleMenu;

@Repository
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    /**
     * 删除角色对应的菜单关系
     * @param roleId
     */
    @Delete("delete " + RoleMenu.TABLE_NAME + " where role_id =#{roleId} ")
    void deleteByRoleId(Long roleId);
    @Delete("delete " + RoleMenu.TABLE_NAME + " where menu_id =#{menuId} ")
    void deleteByMenuId(Long menuId);

}
