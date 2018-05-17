package pers.goetboy.entity.sys;

import pers.goetboy.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * 角色表
 */
@Entity
@Table(name = Role.TABLE_NAME)
public class Role extends AbstractEntity implements Serializable {
    public  final  static String  TABLE_NAME = "sys_role";
    /**
     * 角色名
     */
    @Column(name = "name")
    private  String name;
    /**
     * 角色菜单
     */
    @Transient
    private List<Menu> menus ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", menus=" + menus +
                "} " + super.toString();
    }
}
