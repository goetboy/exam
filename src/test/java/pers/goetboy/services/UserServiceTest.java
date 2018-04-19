package pers.goetboy.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import pers.goetboy.BaseTest;
import pers.goetboy.entity.sys.User;

@EnableAutoConfiguration
public class UserServiceTest extends BaseTest {
    @Autowired
    UserService userService;
    @Test
    public  void testGet(){
        User user =userService.get(1);
        System.out.println(user);
    }
    @Test
    public  void testSave(){
        User user = new User();
        user.setUserName("test1");
        user.setPassword("123456");
        user.setCreateUser(1);
        user.setState(1);
        userService.save(user);
        userService.listAll().forEach(user1 -> System.out.println(user1));
    }
    @Test
    public  void testUpdate(){
        User user = new User();
        user.setId(3);
        user.setUserName("test1");
        user.setPassword("test");
        user.setState(1);

        userService.update(user);
        User user1 =userService.get(3);
        System.out.println(user1);
    }
    @Test
    public  void testDelete(){
        userService.delete(2);
        User user1 =userService.get(2);
        System.out.println(user1);
    }
}
