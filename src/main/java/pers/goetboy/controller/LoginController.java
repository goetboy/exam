package pers.goetboy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import pers.goetboy.entity.sys.User;
import pers.goetboy.services.LoginRepository;
import pers.goetboy.services.UserService;

public class LoginController{

        @Autowired
        private UserService sysUserService;
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
        @PostMapping(value = "/", params = {"username", "password"})
        public String getToken(String username, String password) throws AuthenticationException {
            return sysUserService.login(username, password);
        }

        public User getUser() {

            return loginRepository.getcurrentUser();
        }

        @GetMapping(value = "/")
        public void loginOut() {
            sysUserService.loginOut();
        }

        /**
         * 用户注册
         *
         * @param user 用户信息
         * @return 操作结果
         * @throws AuthenticationException 错误信息
         */
        @PostMapping(value = "/register")
        public String register(@RequestBody User user) throws AuthenticationException {
            return sysUserService.register(user);
        }

        /**
         * 刷新密钥
         *
         * @param authorization 原密钥
         * @return 新密钥
         * @throws AuthenticationException 错误信息
         */
        @GetMapping(value = "/refreshToken")
        public String refreshToken(@RequestHeader String authorization) throws AuthenticationException {
            return sysUserService.refreshToken(authorization);
        }
}
