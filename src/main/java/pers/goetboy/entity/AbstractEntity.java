package pers.goetboy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * 实体顶级属性
 */
@Getter
@Setter
@ToString(callSuper = true)
public class AbstractEntity implements Serializable {
    public static final String SEQ_SUFFIX = "_seq";
    @TableId(type = IdType.INPUT)
    @TableField(value = "id")
    private Long id;
    /**
     * 创造者
     */
    //@NotNull(message = "{table.createUser.notnull}")
    //@Max(value = 10,message = "{table.createUser.max}")
    @TableField("CREATED_USER")
    private Long createdUser;
    /**
     * 创建时间
     */
    @TableField("CREATED_TIME")
    private Date createdTime;
    /**
     * 更新时间
     */
    @TableField("UPDATED_TIME")
    private Date updatedTime;
    /**
     * 更新人
     */
    @TableField("UPDATED_USER")
    private Long updatedUser;

    /**
     * 描述
     */
    @TableField("remark")
    private String remark;
    /**
     * 状态
     */
    @Pattern(regexp = "$[0-1]{1}^", message = "状态 0停用|1异常")
    @TableField("state")
    private Integer state;

}
