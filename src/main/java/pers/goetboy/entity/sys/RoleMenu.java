package pers.goetboy.entity.sys;

import pers.goetboy.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 角色用户映射表
 */
@Entity
@Table(name = RoleMenu.TABLE_NAME)
public class RoleMenu extends AbstractEntity implements Serializable {
    public final static String TABLE_NAME = "sys_role_menu";
    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Long roleId;
    /**
     * 菜单id
     */
    @Column(name = "menu_id")
    private Long menuId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "RoleMenu{" +
                "roleId=" + roleId +
                ", menuId=" + menuId +
                "} " + super.toString();
    }
}
