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
 * 字典表
 *
 * @author goetb
 */
@TableName(value = Dict.TABLE_NAME)
@Getter
@Setter
@ToString(callSuper = true)
@KeySequence(value = Dict.TABLE_NAME + Dict.SEQ_SUFFIX)
public class Dict extends AbstractEntity implements Serializable {
    public final static String TABLE_NAME = "sys_dict";
    @TableField("parent_code")
    private String parentCode;
    /**
     * 编码
     */
    @TableField("code")
    private String code;
    /**
     * 名称
     */
    @TableField("name")
    private String name;
    /**
     * 排序
     */
    @TableField("sort")
    private String sort;
    /**
     * 字典类型
     */
    @TableField("type")
    private String type;

}
