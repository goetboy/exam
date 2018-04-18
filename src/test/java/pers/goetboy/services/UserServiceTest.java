package pers.goetboy.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import pers.goetboy.BaseTest;
import pers.goetboy.entity.User;

@EnableAutoConfiguration
public class UserServiceTest extends BaseTest {
    @Autowired
    UserService userService;
    @Test
    public  void testGet(){
        User user =userService.get(1);
        System.out.println(user);
    }

}
