package pers.goetboy.sys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pers.goetboy.common.exception.service.ServiceTipsException;
import pers.goetboy.sys.model.entity.User;
import pers.goetboy.sys.mapper.UserMapper;
import pers.goetboy.sys.security.JWTUtil;

/**
 * 登陆业务类
 *
 * @author:goetboy
 * @date 2018 /12 /06
 **/
@Service
public class LoginRepository {
    private final
    UserMapper userMapper;
    private final
    AuthenticationManager authenticationManager;
    private final
    JWTUtil jwtUtil;

    @Autowired
    public LoginRepository(UserMapper userMapper, AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;

    }


    /**
     * 登陆
     *
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    public String login(String username, String password) {

        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtUtil.generateToken((User) authentication.getPrincipal());
    }

    /**
     * 注册用户
     *
     * @param user 用户信息
     */
    public void register(User user) throws ServiceTipsException {
        String username = user.getUsername();
        if (userMapper.selectByUsername(username) != null) {
            throw new ServiceTipsException("用户已存在");
        }
        userMapper.insert(encodePassword(user));
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
     * @param oldToken 旧token
     * @return 新token
     */
    public String refreshToken(String oldToken) {
        String token = oldToken.substring(jwtUtil.getJwtConfig().getHeader().length());
        return jwtUtil.refreshToken(token);
    }

    public User getcurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 加密用户密码
     *
     * @param password 密码
     */
    private String encodePassword(String password) {
        final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        return bCryptPasswordEncoder.encode(password);
    }

    /**
     * 加密用户密码
     *
     * @param user 用户
     */
    private User encodePassword(User user) {
        user.setPassword(encodePassword(user.getPassword()));
        return user;
    }


}
