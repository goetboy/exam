package pers.goetboy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pers.goetboy.entity.sys.User;
import pers.goetboy.mapper.UserMapper;
import pers.goetboy.services.LoginRepository;

/**
 * @author:goetboy;
 * @date 2018 /12 /06
 **/
@Service

public class LoginRepository {
    @Autowired
    UserMapper UserMapper;


    public User findByUsername(String username) {
        User user = UserMapper.findByUserName(username);
        return user;

    }
    public User getcurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.findByUsername(userDetails.getUsername());
    }
}
