package pers.goetboy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.goetboy.entity.User;
import pers.goetboy.mapper.UserMapper;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public User get(Integer id)
    {
     return  userMapper.get(id);
    }

}
