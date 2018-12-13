package pers.goetboy.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;
import pers.goetboy.entity.sys.Role;
import pers.goetboy.entity.sys.User;
import pers.goetboy.entity.sys.UserRole;

import java.util.List;

@Repository
public interface UserRoleMapper {


    @Select("select * from " + UserRole.TABLE_NAME)
    public List<UserRole> getAll();

    @Select("select * from " + UserRole.TABLE_NAME + " where id=#{id}")
    public UserRole get(Long id);


    //void insert(Role role);
    @InsertProvider(type = UserRoleMapperProvider.class, method = "insert")
    public Long dynamicInsert(UserRole UserRole);

    //void update(Role role);

    /**
     * 根据传入条件更新
     *
     * @param UserRole
     */
    @UpdateProvider(type = UserRoleMapperProvider.class, method = "update")
    public void dynamicUpdate(UserRole UserRole);

    /**
     * 通过角色id删除用户角色映射信息
     *
     * @param roleId
     */
    @Delete("delete " + UserRole.TABLE_NAME + " where role_id = #{roleId} ")
    public void deleteByRoleId(Long roleId);

    /**
     * 通过用户id删除用户角色映射信息
     *
     * @param userId
     */
    @Delete("delete " + UserRole.TABLE_NAME + " where user_id = #{userId}")
    public void deleteByUserId(Long userId);

    /**
     * 通过用户id删除用户角色映射信息
     *
     * @param userId
     */
    @Delete("delete " + UserRole.TABLE_NAME + " where user_id =#{userId} and role_id =#{roleId}")
    public void deleteByUserIdAndRoleId(Long userId, Long roleId);

    /**
     * 通过id删除
     *
     * @param id
     */
    @Delete("delete from " + UserRole.TABLE_NAME + " where id=#{id}")
    void delete(Long id);

    class UserRoleMapperProvider {
        /**
         * 插入动态语句
         *
         * @param userRole
         * @return
         */
        public String insert(UserRole userRole) {
            return new SQL() {
                {
                    INSERT_INTO(UserRole.TABLE_NAME);
                    if (userRole.getRoleId() != null)
                        VALUES("roleId", "#{roleId}");
                    if (userRole.getUserId() != null)
                        VALUES("userId", "#{userId}");
                    if (userRole.getCreatedUser() != null)
                        VALUES("createdUser", "#{createdUser}");
                    if (userRole.getUpdatedUser() != null)
                        VALUES("updatedUser", "#{updatedUser}");
                    if (userRole.getRemark() != null)
                        VALUES("remark", "#{remark}");
                    if (userRole.getState() != null)
                        VALUES("state", "#{state}");
                    VALUES("created_Time", "SYSDATE");
                    VALUES("updated_Time", "SYSDATE");
                }
            }.toString();
        }

        public String update(UserRole userRole) {
            return new SQL() {
                {
                    UPDATE(UserRole.TABLE_NAME);
                    if (userRole.getUserId() != null)
                        SET("userId=#{userId}");
                    if (userRole.getRoleId() != null)
                        SET("roleId=#{roleId}");
                    if (userRole.getUpdatedUser() != null)
                        SET("updated_User=#{updatedUser}");
                    if (userRole.getRemark() != null)
                        SET("remark=#{remark}");
                    if (userRole.getState() != null)
                        SET("state=#{state}");
                    SET("updated_Time=SYSDATE");
                    WHERE("id=#{id}");

                }
            }.toString();
        }

        public String select(UserRole userRole) {
            return new SQL() {
                {
                    SELECT("id,userId,roleId,state");
                    FROM(UserRole.TABLE_NAME);
                    if (userRole.getId() != null)
                        WHERE("id=#{id}");
                    if (userRole.getUserId() != null)
                        WHERE("userId=#{userId}");
                    if (userRole.getRoleId() != null)
                        WHERE("roleId=#{roleId}");
                }
            }.toString();
        }
    }
}
