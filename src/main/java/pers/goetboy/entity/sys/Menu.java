package pers.goetboy.entity.sys;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pers.goetboy.entity.AbstractEntity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * 菜单表
 *
 * @author goetb
 */
@TableName(value = Menu.TABLE_NAME)
@Getter
@Setter
@ToString(callSuper = true)
@KeySequence(value = Menu.TABLE_NAME + Menu.SEQ_SUFFIX, clazz = Long.class)

public class Menu extends AbstractEntity implements Serializable {
    public final static String TABLE_NAME = "sys_menu";
    /**
     * 角色名
     */
    @NotNull(message = "{menu.name.notnull}")
    @Size(max = 20, message = "{menu.name.size}")
    @TableField("name")
    private String name;
    /**
     * 菜单地址
     */
    @TableField("url")
    private String url;
    /**
     * 菜单类型
     */
    @TableField("type")
    private Integer type;
    /**
     * 父菜单id
     */
    @TableField("parent_id")
    private Long parentId;
    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;
    /**
     * 角色列表
     */
    @TableField(exist = false)
    private List<Role> roles;

}
