package pers.goetboy.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;
import pers.goetboy.entity.sys.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 用户管理mapper
 */
@Repository
public interface UserMapper extends Mapper<User> {
    String TABLE_NAME = User.TABLE_NAME;


    @Select("select id,userName,password,state from " + TABLE_NAME + " where username=#{username}")
    User  findByUsername(String username);



}
