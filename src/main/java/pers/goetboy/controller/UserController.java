package pers.goetboy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pers.goetboy.entity.sys.User;
import pers.goetboy.services.UserService;

@RestController()
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public User get(Integer id){
        return userService.get(id);

    }

}
