package pers.goetboy.entity.sys;

import pers.goetboy.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 用户表
 */
@Entity
@Table(name = User.TABLE_NAME)
public class User extends AbstractEntity implements Serializable {
    public  final  static String  TABLE_NAME = "sys_user";
    /**
     * 用户名
     */
    private  String userName;
    /**
     * 密码
     */
    private  String password;

    /**
     * 昵称
     */
    private  String nickName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                "} " + super.toString();
    }
}
