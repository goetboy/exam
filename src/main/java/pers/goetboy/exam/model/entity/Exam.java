package pers.goetboy.exam.model.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pers.goetboy.common.AbstractEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 考试类
 *
 * @author:goetb
 * @date 2019 /02 /18
 **/

@TableName(value = Exam.TABLE_NAME)
@Getter
@Setter
@ToString(callSuper = true)
@KeySequence(value = Exam.TABLE_NAME + Exam.SEQ_SUFFIX)
public class Exam extends AbstractEntity implements Serializable {
    public final static String TABLE_NAME = "exam";
    /**
     * 标题
     */
    @TableField("title")
    private String title;
    /**
     * 开始时间
     */
    @TableField("start_date")
    private Date startDate;
    /**
     * 结束时间
     */
    @TableField("end_date")
    private Date endDate;


}
