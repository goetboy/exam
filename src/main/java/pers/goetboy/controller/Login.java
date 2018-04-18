package pers.goetboy.controller;

import pers.goetboy.services.LoginServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Login {
    @Autowired
    LoginServices loginServices;

    /**
     * 登陆
     * @param userName
     * @return
     */
    public String login(String userName) {
        return loginServices.login(userName);
    }
}
