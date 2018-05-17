package pers.goetboy.entity.sys;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pers.goetboy.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户表
 */
@Entity
@Table(name = User.TABLE_NAME)
public class User extends AbstractEntity implements UserDetails {
    public   final  static String  TABLE_NAME = "sys_user";
    /**
     * 用户名
     */
    @Column(name = "userName")
    private  String username;
    /**
     * 密码
     */
    @Column(name = "password")
    private  String password;

    /**
     * 昵称
     */
    @Column(name = "nickName")
    private  String nickName;
    /**
     * 用户角色
     */
    @Transient
    private  List<Role> roles;
    public User() {
    }

    public User(String username, String password) {

        this.username = username;
        this.password = password;
    }

    public User(Long id, String username, String password) {
        super.setId(id);
        this.username = username;
        this.password = password;
    }

    /**
     * 用户菜单权限
     */
    @Transient
    private List<Menu> menus;
    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        List<Role> roles = this.getRoles();
        for (Role role : roles) {
            auths.add(new SimpleGrantedAuthority(role.getName()));
        }
        return auths;
    }



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public String toString() {
        return "User{" +
                "userName='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", roles=" + roles +
                ", menus=" + menus +
                "} " + super.toString();
    }
}
