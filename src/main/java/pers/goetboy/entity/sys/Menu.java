package pers.goetboy.entity.sys;

import pers.goetboy.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * 菜单表
 */
@Entity
@Table(name = Menu.TABLE_NAME)
public class Menu extends AbstractEntity implements Serializable {
    public final static String TABLE_NAME = "sys_menu";
    /**
     * 角色名
     */
    @NotNull(message = "{menu.name.notnull}")
    @Size(max = 20,message = "{menu.name.size}")
    @Column(name = "name")
    private String name;
    /**
     * 菜单地址
     */
    @Column(name = "url")
    private String url;
    /**
     * 菜单类型
     */
    @Column(name = "type")
    private Integer type;
    /**
     * 父菜单id
     */
    @Column(name = "parent")
    private Integer parent;
    /**
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;
    @Transient
    private List<Role> roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", type=" + type +
                ", parent=" + parent +
                ", sort=" + sort +
                ", roles=" + roles +
                "} " + super.toString();
    }
}
