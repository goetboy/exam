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
@Table(name = RoleUser.TABLE_NAME)
public class RoleUser extends AbstractEntity implements Serializable {
    public final static String TABLE_NAME = "sys_role_user";
    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Integer roleId;
    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "RoleUser{" +
                "roleId=" + roleId +
                ", userId=" + userId +
                "} " + super.toString();
    }
}
