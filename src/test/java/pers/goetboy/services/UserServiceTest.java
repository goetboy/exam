package pers.goetboy.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.ToString;
import org.apache.commons.codec.Decoder;
import org.apache.commons.codec.digest.Md5Crypt;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.codec.Hex;
import pers.goetboy.BaseTest;
import pers.goetboy.entity.sys.User;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@EnableAutoConfiguration
public class UserServiceTest extends BaseTest {
    @Autowired
    UserService userService;

    @Test
    public void testGet() {
        User user = userService.get(1);
        System.out.println(user);
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123456");
        user.setCreatedUser(1);
        user.setState(1);
        user.setCreatedTime(new Date());
        user.setRemark("测试");
        userService.saveUser(user);
        userService.listUser().forEach(user1 -> System.out.println(user1));
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(3);
        user.setUsername("test1");
        user.setPassword("test");
        user.setState(1);
        user.setCreatedTime(new Date());

        //  userService.updateUser(user);
        User user1 = userService.get(3);
        System.out.println(user1);
    }

    @Test
    public void testDelete() {
        userService.deleteUser(2);
        User user1 = userService.get(2);
        System.out.println(user1);
    }

    @Test
    public void testLogin() {
        //    userService.login("admin","123456");
    }

    @Test
    public void testList() {
        List<User> users = userService.listUser();
        users.forEach(user -> System.out.println(user));
        ObjectMapper objectMapper = new ObjectMapper();
        users.forEach(user -> {
            try {
                System.out.println(   objectMapper.writeValueAsString(user));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });

    }
}
