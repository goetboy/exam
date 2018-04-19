package pers.goetboy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.goetboy.entity.sys.User;
import pers.goetboy.mapper.UserMapper;

import java.util.List;

@Service
public class UserService extends AbstractService<User> {
    @Autowired
    UserMapper userMapper;
    @Override
    public User get(Integer id) {
        return userMapper.get(id);
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    List<User> listAll() {
        return userMapper.getAll();
    }

    @Override
    void save(User user) {
        userMapper.dynamicInsert(user);
    }

    @Override
    void update(User user) {
        userMapper.dynamicUpdate(user);
    }

    @Override
    void delete(Integer id) {
        userMapper.delete(id);
    }


}
