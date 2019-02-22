package pers.goetboy.exam.model.entity;

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
 * 问题类
 *
 * @author:goetb
 * @date 2019 /02 /18
 **/

@TableName(value = Question.TABLE_NAME)
@Getter
@Setter
@ToString(callSuper = true)
@KeySequence(value = Question.TABLE_NAME + Question.SEQ_SUFFIX)
public class Question extends AbstractEntity implements Serializable {
    public final static String TABLE_NAME = "question";
    /**
     * 试题类型
     */
    @TableField(value = "type")
    private Integer type;
    /**
     * 试题内容
     */
    @TableField(value = "context")
    private String context;
    /**
     * 答案
     */
    @TableField(exist = false)
    private List<Answer> answers;
}
