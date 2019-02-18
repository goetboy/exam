package pers.goetboy.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 问题类
 *
 * @author:goetb
 * @date 2019 /02 /18
 **/

@TableName(value = Answer.TABLE_NAME)
@Getter
@Setter
@ToString(callSuper = true)
@KeySequence(value = Answer.TABLE_NAME + Answer.SEQ_SUFFIX)
public class Answer extends AbstractEntity implements Serializable {
    public final static String TABLE_NAME = "answer";
    /**
     * 问题id
     */
    @TableField("question_id")
    private Long questionId;
    /**
     * 类型
     */
    @TableField("type")
    private Integer type;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 答案内容
     */
    private String context;
    /**
     * 是否是正确答案 0否|1是
     */
    private String right;

}
