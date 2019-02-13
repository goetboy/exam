package pers.goetboy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import pers.goetboy.entity.sys.User;

/**
 * 用户管理mapper
 * @author goetb
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    String TABLE_NAME = User.TABLE_NAME;

    /**
     * 通过用户名查询用户
     *
     * @param username
     * @return
     */
    @Select("select id,userName,password,state from " + TABLE_NAME + " where username=#{username}")
    User selectByUsername(String username);


}
