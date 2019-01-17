package pers.goetboy.entity.sys;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
@Getter
@Setter
@ToString(callSuper = true)
public class RoleMenu extends AbstractEntity implements Serializable {
    public final static String TABLE_NAME = "sys_role_menu";
    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Integer roleId;
    /**
     * 菜单id
     */
    @Column(name = "menu_id")
    private Integer menuId;


}
