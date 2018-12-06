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
public class AbstractEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 创造者
     */
    //@NotNull(message = "{table.createUser.notnull}")
    //@Max(value = 10,message = "{table.createUser.max}")
    @Column(name = "created_User")
    private Integer createdUser;
    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Timestamp createdTime;
    /**
     * 更新时间
     */
    @Column(name = "updated_Time")
    private Timestamp updatedTime;
    /**
     * 更新人
     */
    @Column(name = "updated_User")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(Integer createdUser) {
        this.createdUser = createdUser;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(Integer updatedUser) {
        this.updatedUser = updatedUser;
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
                ", createdUser=" + createdUser +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", updatedUser=" + updatedUser +
                ", remark='" + remark + '\'' +
                ", state=" + state +
                '}';
    }
}
