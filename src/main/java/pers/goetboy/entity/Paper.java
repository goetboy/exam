package pers.goetboy.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 试卷
 *
 * @author:goetb
 * @date 2019 /02 /18
 **/

@TableName(value = Paper.TABLE_NAME)
@Getter
@Setter
@ToString(callSuper = true)
@KeySequence(value = Paper.TABLE_NAME + Paper.SEQ_SUFFIX)
public class Paper extends AbstractEntity implements Serializable {
    public final static String TABLE_NAME = "paper";
    /**
     * 试卷保存路径
     */
    @TableField("path")
    private String path;
    /**
     * 试卷类型
     */
    @TableField("type")
    private String type;
    /**
     * 分值
     */
    @TableField("score")
    private Integer score;
    /**
     * 及格分
     */
    @TableField("pass")
    private Integer pass;


}
