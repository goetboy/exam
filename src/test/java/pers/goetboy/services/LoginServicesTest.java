package pers.goetboy.services;

import pers.goetboy.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
public class LoginServicesTest extends BaseTest {
    @Autowired
    LoginServices loginServices;
    @Test
    public void testLogin(){
        System.out.println(loginServices.login("张三"));
    }
}
