package pers.goetboy.exam.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import pers.goetboy.BaseTest;
import pers.goetboy.sys.model.entity.User;
import pers.goetboy.sys.services.UserService;

import java.util.Date;

@EnableAutoConfiguration
public class UserServiceTest extends BaseTest {
    @Autowired
    UserService userService;

    @Test
    public void testGet() {
        User user = userService.get(1L);
        System.out.println(user);
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123456");
        user.setCreatedUser(1L);
        user.setState(1);
        user.setCreatedTime(new Date());
        user.setRemark("测试");
        userService.save(user);
        userService.page(new Page(1, Integer.MAX_VALUE)).getRecords().forEach(System.out::println);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(3L);
        user.setUsername("test1");
        user.setPassword("test");
        user.setState(1);
        user.setCreatedTime(new Date());

        //  userService.updateUser(user);
        User user1 = userService.get(3L);
        System.out.println(user1);
    }

    @Test
    public void testDelete() {
        userService.delete(2L);
        User user1 = userService.get(2L);
        System.out.println(user1);
    }

    @Test
    public void testLogin() {
        //    userService.login("admin","123456");
    }

    @Test
    public void testList() {
        IPage<User> userIPage = userService.page(new Page(1, Integer.MAX_VALUE));
        userIPage.getRecords().forEach(System.out::println);
        ObjectMapper objectMapper = new ObjectMapper();

        userIPage.getRecords().forEach(user -> {
            try {
                System.out.println(objectMapper.writeValueAsString(user));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });

    }
}
