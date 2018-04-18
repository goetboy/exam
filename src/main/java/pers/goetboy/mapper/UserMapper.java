package pers.goetboy.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import pers.goetboy.entity.User;

import java.util.List;
@Repository
public interface UserMapper {
    @Select("select * from user")
    List<User> getAll();

    @Select("select * from user where id=id")
    User get(Integer id);

    @Insert("insert into( userName, password, createUser, createTime, updateTime, updateUser) values (#{userName},#{password},#{createUser},#{createTime},#{updateTime},#{updateUser})")
    void insert(User user);

    @Update("update set userName=#{userName},password=#{password},updateTime={updateTime},updateUser={updateUser} where id=#{id}")
    void update(User user);

    @Delete("delete from User where id=#{id}")
    void delete(Integer id);

}
