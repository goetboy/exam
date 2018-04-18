package pers.goetboy.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

@Entity
public abstract class AbstractEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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
     * 创建时间
     */
    @Column(name = "createTime")
    private Timestamp createTime;
    /**
     * 创造者
     */
    @Column(name = "createUser")
    private Integer createUser;
    /**
     * 状态
     */
    @Pattern(regexp = "$[0-1]{1}^",message = "状态 0停用|1异常")
    @Column(name = "state")
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
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
                ", updateTime=" + updateTime +
                ", updateUser=" + updateUser +
                ", createTime=" + createTime +
                ", createUser=" + createUser +
                ", state=" + state +
                '}';
    }
}
