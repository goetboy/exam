package pers.goetboy.controller;

import com.goetboy.exception.service.BaseServiceTipsMsgException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import pers.goetboy.entity.sys.User;
import pers.goetboy.services.LoginRepository;

@RestController
@RequestMapping("/")
@ResponseBody
public class LoginController {

    @Autowired
    private LoginRepository loginRepository;

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 操作结果
     * @throws AuthenticationException 错误信息
     */
    @PostMapping(value = "/login", params = {"username", "password"})
    public String getToken(String username, String password) {
        return loginRepository.login(username, password);
    }

    public User getUser() {

        return loginRepository.getcurrentUser();
    }

    @GetMapping(value = "/login")
    public void loginOut() {
        loginRepository.loginOut();
    }

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return 操作结果
     * @throws AuthenticationException 错误信息
     */
    @PostMapping(value = "/register")
    public void register(@RequestBody User user) throws BaseServiceTipsMsgException {
        loginRepository.register(user);
    }

    /**
     * 刷新密钥
     *
     * @param authorization 原密钥
     * @return 新密钥
     * @throws AuthenticationException 错误信息
     */
    @GetMapping(value = "/refreshToken")
    public String refreshToken(@RequestHeader("Authorization") String authorization) {
        return loginRepository.refreshToken(authorization);
    }
}
