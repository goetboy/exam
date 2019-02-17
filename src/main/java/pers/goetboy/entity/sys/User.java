package pers.goetboy.entity.sys;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pers.goetboy.entity.AbstractEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户表
 * @author goetb
 */
@TableName(value = User.TABLE_NAME)
@Getter
@Setter
@ToString(callSuper = true)
@KeySequence(value = User.TABLE_NAME+User.SEQ_SUFFIX,clazz = Long.class)
public class User extends AbstractEntity implements UserDetails {
    public final static String TABLE_NAME = "sys_user";
    /**
     * 用户名
     */
    @TableField( "userName")
    private String username;
    /**
     * 昵称
     */
    @TableField( "nickName")
    private String nickName;
    /**
     * 密码
     */
    @TableField( "password")
    private String password;
    @TableField( "name")
    private String name;
    @TableField( "phone")
    private String phone;
    @TableField( "address")
    private String address;
    @TableField( "email")
    private String email;
    @TableField(exist = false)
    private List<Role> roles;
    /**
     * 用户菜单权限
     */
    @TableField(exist = false )
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
