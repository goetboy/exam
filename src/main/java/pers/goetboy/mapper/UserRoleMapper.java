package pers.goetboy.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;
import pers.goetboy.entity.sys.Role;
import pers.goetboy.entity.sys.User;
import pers.goetboy.entity.sys.UserRole;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface UserRoleMapper extends Mapper<UserRole> {



    /**
     * 通过角色id删除用户角色映射信息
     *
     * @param roleId
     */
    @Delete("delete " + UserRole.TABLE_NAME + " where role_id = #{roleId} ")
    void deleteByRoleId(Integer roleId);

    /**
     * 通过用户id删除用户角色映射信息
     *
     * @param userId
     */
    @Delete("delete " + UserRole.TABLE_NAME + " where user_id = #{userId}")
    void deleteByUserId(Integer userId);

    /**
     * 通过用户id删除用户角色映射信息
     *
     * @param userId
     */
    @Delete("delete " + UserRole.TABLE_NAME + " where user_id =#{0} and role_id =#{1}")
    void deleteByUserIdAndRoleId(Integer userId, Integer roleId);


}
