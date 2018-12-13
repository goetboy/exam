package pers.goetboy.entity.sys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.collections4.CollectionUtils;
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
@Data
@ToString
public class User extends AbstractEntity implements UserDetails {
    public final static String TABLE_NAME = "sys_user";
    /**
     * 用户名
     */
    @Column(name = "userName")
    private String username;
    /**
     * 密码
     */
    @Column(name = "password")
    private String password;
    @Transient
    private List<Role> roles;
    /**
     * 用户菜单权限
     */
    @Transient
    private List<Menu> menus;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        List<Role> roles = this.getRoles();
        if (CollectionUtils.isNotEmpty(roles)) {
            for (Role role : roles) {
                auths.add(new SimpleGrantedAuthority(role.getName()));
            }
        }
        return auths;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return this.password;
    }
    @Override
    public String getUsername() {
        return this.username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

}
