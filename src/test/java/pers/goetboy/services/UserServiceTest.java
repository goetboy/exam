package pers.goetboy.services;

import lombok.ToString;
import org.apache.commons.codec.Decoder;
import org.apache.commons.codec.digest.Md5Crypt;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.codec.Hex;
import pers.goetboy.BaseTest;
import pers.goetboy.entity.sys.User;

import java.util.Base64;

@EnableAutoConfiguration
public class UserServiceTest extends BaseTest {
    @Autowired
    UserService userService;
    @Test
    public  void testGet(){
        User user =userService.get(1L);
        System.out.println(user);
    }
    @Test
    public  void testSave(){
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123456");
        user.setCreatedUser(1);
        user.setState(1);
        //userService.(user);
        userService.listUser().forEach(user1 -> System.out.println(user1));
    }
    @Test
    public  void testUpdate(){
        User user = new User();
        user.setId(3L);
        user.setUsername("test1");
        user.setPassword("test");
        user.setState(1);

      //  userService.updateUser(user);
        User user1 =userService.get(3L);
        System.out.println(user1);
    }
    @Test
    public  void testDelete(){
        userService.deleteUser(2L);
        User user1 =userService.get(2L);
        System.out.println(user1);
    }
    @Test
    public void testLogin(){
    //    userService.login("admin","123456");
    }

}
