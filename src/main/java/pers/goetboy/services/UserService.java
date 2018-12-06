package pers.goetboy.services;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pers.goetboy.common.exception.service.ServiceTipsException;
import pers.goetboy.entity.sys.Role;
import pers.goetboy.entity.sys.RoleUser;
import pers.goetboy.entity.sys.User;
import pers.goetboy.mapper.RoleUserMapper;
import pers.goetboy.mapper.UserMapper;
import pers.goetboy.security.JWTUtil;
import pers.goetboy.utils.DataBus;

import java.util.*;

/**
 * @author goetb
 * @date 2018年12月6日 11点08分
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    RoleUserMapper roleUserMapper;
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JWTUtil jwtUtil;
    private LoginRepository loginRepository;

    @Autowired
    public UserService(AuthenticationManager authenticationManager, @Qualifier("jwtUserDetail") UserDetailsService userDetailsService, JWTUtil jwtUtil, LoginRepository loginRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.loginRepository = loginRepository;
    }


    public User get(Long id) {
        return userMapper.get(id);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<User> findAll() {
        return userMapper.getAll();
    }

    /**
     * 保存用户信息和用户对应的角色信息
     *
     * @param user
     */
    public Long save(User user) {

        Long id = userMapper.dynamicInsert(encodePassword(user));

        List<Role> roles = user.getRoles();
        if (roles != null) {
            RoleUser roleUser = new RoleUser();
            roleUser.setRoleId(roles.get(0).getId());
            roleUser.setUserId(id);
            roleUserMapper.dynamicInsert(roleUser);
        }

        return id;
    }

    /**
     * 更新用户信息和用户对应的角色信息
     *
     * @param user 用户信息
     */

    public void updateUser(User user) {


        userMapper.dynamicUpdate(encodePassword(user));

        List<Role> roles = user.getRoles();
        if (CollectionUtils.isNotEmpty(roles)) {
            roleUserMapper.deleteByUserId(user.getId());
            RoleUser roleUser = new RoleUser();
            roleUser.setRoleId(roles.get(0).getId());
            roleUser.setUserId(user.getId());
            roleUserMapper.dynamicInsert(roleUser);
        }

    }

    /**
     * 更新用户密码
     *
     * @param id       用户id
     * @param password 密码
     * @throws ServiceTipsException
     */
    public void updatePassword(Long id, String password) throws ServiceTipsException {
        User user = userMapper.get(id);
        if (user == null) {
            throw new ServiceTipsException("找不到对应的用户信息");
        }
        user.setPassword(password);
        userMapper.dynamicUpdate(encodePassword(user));
    }

    /**
     * 删除用户信息以及用户对应的角色映射信息
     *
     * @param id 用户id
     */

    public void deleteUser(Long id) {
        userMapper.delete(id);
        roleUserMapper.deleteByUserId(id);
    }

    /**
     * 登陆
     *
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    public String login(String username, String password) {


        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        String token = jwtUtil.generateToken(userDetails);
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return token;
    }

    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    public String register(User user) {
        String username = user.getUsername();
        if (loginRepository.findByUsername(username) != null) {
            return "用户已存在";
        }

        save(user);

        return "success";
    }

    /**
     * 登出
     */
    public void loginOut() {
        SecurityContextHolder.clearContext();
    }

    /**
     * 刷新token
     *
     * @param oldToken
     * @return
     */
    public String refreshToken(String oldToken) {
        String token = oldToken.substring("Bearer ".length());
        return jwtUtil.refreshToken(token);
    }

    /**
     * 加密用户密码
     *
     * @param user
     */
    private User encodePassword(User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return user;
    }
}
