package pers.goetboy.entity.sys;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pers.goetboy.entity.AbstractEntity;

import java.io.Serializable;

/**
 * 角色表
 *
 * @author goetb
 */
@TableName(value = Group.TABLE_NAME)
@Getter
@Setter
@ToString(callSuper = true)
@KeySequence(value = Group.TABLE_NAME+Group.SEQ_SUFFIX,clazz = Long.class)
public class Group extends AbstractEntity implements Serializable {
    public final static String TABLE_NAME = "sys_group";
    @TableField( "parent_id")
    private Long parentId;

}
