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
@Table(name = UserRole.TABLE_NAME)
@Getter
@Setter
@ToString(callSuper = true)
public class UserRole extends AbstractEntity implements Serializable {
    public final static String TABLE_NAME = "sys_user_role";
    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Long roleId;
    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

}
