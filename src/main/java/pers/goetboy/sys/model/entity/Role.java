package pers.goetboy.sys.model.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pers.goetboy.common.AbstractEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 角色表
 */
@TableName(Role.TABLE_NAME)
@Getter
@Setter
@ToString(callSuper = true)
@KeySequence(value = Role.TABLE_NAME+Role.SEQ_SUFFIX,clazz = Long.class)

public class Role extends AbstractEntity implements Serializable {
    public  final  static String  TABLE_NAME = "sys_role";
    /**
     * 角色名
     */
    @TableField( "name")
    private  String name;
    /**
     * 角色菜单
     */
    @TableField(exist = false)
    private List<Menu> menus ;

}
