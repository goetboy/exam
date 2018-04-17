package com.goetboy.services;

import org.springframework.stereotype.Service;

@Service
public class LoginServices {
    public String login(String userName) {

        return "欢迎" + userName + "登陆";
    }
}
