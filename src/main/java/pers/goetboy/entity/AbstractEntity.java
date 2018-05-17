package pers.goetboy.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 实体顶级属性
 */
@Entity
public class AbstractEntity  implements Serializable{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 创造者
     */
    //@NotNull(message = "{table.createUser.notnull}")
    //@Max(value = 10,message = "{table.createUser.max}")
    @Column(name = "createUser")
    private Integer createUser;
    /**
     * 创建时间
     */
    @Column(name = "createTime")
    private Timestamp createTime;
    /**
     * 更新时间
     */
    @Column(name = "updateTime")
    private Timestamp updateTime;
    /**
     * 更新人
     */
    @Column(name = "updateUser")
    private Integer updateUser;

    /**
     * 描述
     */
    private String remark;
    /**
     * 状态
     */
    @Pattern(regexp = "$[0-1]{1}^", message = "状态 0停用|1异常")
    @Column(name = "state")
    private Integer state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "AbstractEntity{" +
                "id=" + id +
                ", createUser=" + createUser +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", updateUser=" + updateUser +
                ", remark='" + remark + '\'' +
                ", state=" + state +
                '}';
    }
}
