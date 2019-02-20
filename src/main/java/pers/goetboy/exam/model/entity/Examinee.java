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
 * 问题类
 *
 * @author:goetb
 * @date 2019 /02 /18
 **/

@TableName(value = Examinee.TABLE_NAME)
@Getter
@Setter
@ToString(callSuper = true)
@KeySequence(value = Examinee.TABLE_NAME + Examinee.SEQ_SUFFIX)
public class Examinee extends AbstractEntity implements Serializable {
    public final static String TABLE_NAME = "examinee";
    /**
     * 姓名
     */
    @TableField("name")
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 性别 1男|2女|9未知
     */
    private Integer sex;
    /**
     * 出生日期
     */
    private Date birthday;
    /**
     * 识别号
     */
    private String identifyNumber;

}
