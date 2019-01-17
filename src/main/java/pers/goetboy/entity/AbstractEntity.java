package pers.goetboy.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 实体顶级属性
 */
@Entity
@Getter
@Setter
@ToString(callSuper = true)
public class AbstractEntity implements Serializable {
    @Id
    @Column(name = "id")
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * 创造者
     */
    //@NotNull(message = "{table.createUser.notnull}")
    //@Max(value = 10,message = "{table.createUser.max}")
    @Column(name = "CREATED_USER")
    private Integer createdUser;
    /**
     * 创建时间
     */
    @Column(name = "CREATED_TIME")
    private Date createdTime;
    /**
     * 更新时间
     */
    @Column(name = "UPDATED_TIME")
    private Date updatedTime;
    /**
     * 更新人
     */
    @Column(name = "UPDATED_USER")
    private Integer updatedUser;

    /**
     * 描述
     */
    @Column(name = "remark")
    private String remark;
    /**
     * 状态
     */
    @Pattern(regexp = "$[0-1]{1}^", message = "状态 0停用|1异常")
    @Column(name = "state")
    private Integer state;

}
