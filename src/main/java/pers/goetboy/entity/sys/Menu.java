package pers.goetboy.entity.sys;

import pers.goetboy.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

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

    private String name;
    /**
     * 菜单地址
     */
    private String url;
    /**
     * 菜单类型
     */
    private Integer type;
    /**
     * 父菜单id
     */
    private Integer parent;
    /**
     * 排序
     */
    private Integer sort;

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

    @Override
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", type=" + type +
                ", parent=" + parent +
                ", sort=" + sort +
                "} " + super.toString();
    }
}
