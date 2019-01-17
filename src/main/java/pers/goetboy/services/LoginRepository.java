package pers.goetboy.services;

import com.goetboy.core.exception.service.BaseServiceTipsMsgException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pers.goetboy.entity.sys.User;
import pers.goetboy.mapper.RoleMapper;
import pers.goetboy.mapper.UserMapper;
import pers.goetboy.security.JWTUtil;

/**
 * @author:goetboy;
 * @date 2018 /12 /06
 **/
@Service
public class LoginRepository {
    @Autowired
    UserMapper userMapper;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JWTUtil jwtUtil;
    @Autowired
    RoleMapper roleMapper;


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
        UserDetails userDetails = userMapper.findByUsername(username);
        String token = jwtUtil.generateToken(userDetails);
        return token;
    }

    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    public void register(User user) throws BaseServiceTipsMsgException {
        String username = user.getUsername();
        if (userMapper.findByUsername(username) != null) {
            throw new BaseServiceTipsMsgException("用户已存在");
        }
        userMapper.insertSelective(encodePassword(user));
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
        String token = oldToken.substring(jwtUtil.getJwtConfig().getHeader().length());
        return jwtUtil.refreshToken(token);
    }

    public User getcurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 加密用户密码
     *
     * @param password
     */
    private String encodePassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        return bCryptPasswordEncoder.encode(password);
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
