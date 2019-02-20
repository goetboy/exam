package pers.goetboy.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Repository;
import pers.goetboy.sys.model.entity.UserRole;

/**
 * 用户角色信息mapper
 *
 * @author goetb
 */
@Repository
public interface UserRoleMapper extends BaseMapper<UserRole> {


    /**
     * 通过角色id删除用户角色映射信息
     *
     * @param roleId 角色id
     */
    @Delete("delete " + UserRole.TABLE_NAME + " where role_id = #{roleId} ")
    void deleteByRoleId(Long roleId);

    /**
     * 通过用户id删除用户角色映射信息
     *
     * @param userId
     */
    @Delete("delete " + UserRole.TABLE_NAME + " where user_id = #{userId}")
    void deleteByUserId(Long userId);

    /**
     * 通过用户id删除用户角色映射信息
     *
     * @param userId 用户id
     * @param roleId 角色id
     */
    @Delete("delete " + UserRole.TABLE_NAME + " where user_id =#{arg0} and role_id =#{arg0}")
    void deleteByUserIdAndRoleId(Long userId, Long roleId);


}
