package pers.goetboy.entity.sys;

import pers.goetboy.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

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

    private  String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
