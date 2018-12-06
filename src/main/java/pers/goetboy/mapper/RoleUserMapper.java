package pers.goetboy.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;
import pers.goetboy.entity.sys.Role;
import pers.goetboy.entity.sys.RoleUser;

import java.util.List;

@Repository
public interface RoleUserMapper {



    @Select("select * from " + RoleUser.TABLE_NAME)
    public List<RoleUser> getAll();

    @Select("select * from " + RoleUser.TABLE_NAME + " where id=#{id}")
    public RoleUser get(Long id);


    //void insert(Role role);
    @InsertProvider(type = RoleUserMapperProvider.class, method = "insert")
    public Long dynamicInsert(RoleUser roleUser);

    //void update(Role role);

    /**
     * 根据传入条件更新
     *
     * @param roleUser
     */
    @UpdateProvider(type = RoleUserMapperProvider.class, method = "update")
    public void dynamicUpdate(RoleUser roleUser);

    /**
     * 通过角色id删除用户角色映射信息
     * @param roleId
     */
    @Delete("delete " +RoleUser.TABLE_NAME+ " where role_id in (select id from "+ Role.TABLE_NAME +" where id=#{roleId} ) ")
    public void deleteByRoleId(Long roleId);

    /**
     * 通过用户id删除用户角色映射信息
     * @param userId
     */
    @Delete("delete " +RoleUser.TABLE_NAME+ " where role_id in (select id from "+ Role.TABLE_NAME +" where id=#{userId} ) ")
    public void deleteByUserId(Long userId);
    /**
     * 通过id删除
     *
     * @param id
     */
    @Delete("delete from " + RoleUser.TABLE_NAME + " where id=#{id}")
    void delete(Long id);

    class RoleUserMapperProvider {
        /**
         * 插入动态语句
         *
         * @param roleUser
         * @return
         */
        public String insert(RoleUser roleUser) {
            return new SQL() {
                {
                    INSERT_INTO(RoleUser.TABLE_NAME);
                    if (roleUser.getRoleId() != null)
                        VALUES("roleId", "#{roleId}");
                    if (roleUser.getUserId() != null)
                        VALUES("userId", "#{userId}");
                    if (roleUser.getCreatedUser() != null)
                        VALUES("createdUser", "#{createdUser}");
                    if (roleUser.getUpdatedUser() != null)
                        VALUES("updatedUser", "#{updatedUser}");
                    if (roleUser.getRemark() != null)
                        VALUES("remark", "#{remark}");
                    if (roleUser.getState() != null)
                        VALUES("state", "#{state}");
                    VALUES("created_Time", "SYSDATE");
                    VALUES("updated_Time", "SYSDATE");
                }
            }.toString();
        }

        public String update(RoleUser roleUser) {
            return new SQL() {
                {
                    UPDATE(RoleUser.TABLE_NAME);
                    if (roleUser.getUserId() != null)
                        SET("userId=#{userId}");
                    if (roleUser.getRoleId() != null)
                        SET("roleId=#{roleId}");
                    if (roleUser.getUpdatedUser() != null)
                        SET("updated_User=#{updatedUser}");
                    if (roleUser.getRemark() != null)
                        SET("remark=#{remark}");
                    if (roleUser.getState() != null)
                        SET("state=#{state}");
                    SET("updated_Time=SYSDATE");
                    WHERE("id=#{id}");

                }
            }.toString();
        }

        public String select(RoleUser roleUser) {
            return new SQL() {
                {
                    SELECT("id,userId,roleId,state");
                    FROM(RoleUser.TABLE_NAME);
                    if (roleUser.getId() != null)
                        WHERE("id=#{id}");
                    if (roleUser.getUserId() != null)
                        WHERE("userId=#{userId}");
                    if (roleUser.getRoleId() != null)
                        WHERE("roleId=#{roleId}");
                }
            }.toString();
        }
    }
}
