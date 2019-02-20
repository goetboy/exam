package pers.goetboy.sys.model.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pers.goetboy.common.AbstractEntity;

import java.io.Serializable;

/**
 * 角色用户映射表
 * @author goetb
 */
@TableName(value = UserRole.TABLE_NAME)
@Getter
@Setter
@ToString(callSuper = true)
@KeySequence(value = UserRole.TABLE_NAME+UserRole.SEQ_SUFFIX,clazz = Long.class)
public class UserRole extends AbstractEntity implements Serializable {
    public final static String TABLE_NAME = "sys_user_role";
    /**
     * 角色id
     */
    @TableField( "role_id")
    private Long roleId;
    /**
     * 用户id
     */
    @TableField( "user_id")
    private Long userId;

}
