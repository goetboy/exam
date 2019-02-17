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
 * 角色用户映射表
 * @author goetb
 */
@TableName(value = RoleMenu.TABLE_NAME)
@Getter
@Setter
@ToString(callSuper = true)
@KeySequence(value = RoleMenu.TABLE_NAME+RoleMenu.SEQ_SUFFIX,clazz = Long.class)

public class RoleMenu extends AbstractEntity implements Serializable {
    public final static String TABLE_NAME = "sys_role_menu";
    /**
     * 角色id
     */
    @TableField( "role_id")
    private Long roleId;
    /**
     * 菜单id
     */
    @TableField( "menu_id")
    private Long menuId;


}
