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

   static final String TABLE_NAME = RoleUser.TABLE_NAME;

    @Select("select * from " + TABLE_NAME)
    List<RoleUser> getAll();

    @Select("select * from " + TABLE_NAME + " where id=#{id}")
    RoleUser get(Integer id);


    //void insert(Role role);
    @InsertProvider(type = RoleUserMapperProvider.class, method = "insert")
    void dynamicInsert(RoleUser roleUser);

    //void update(Role role);

    /**
     * 根据传入条件更新
     *
     * @param roleUser
     */
    @UpdateProvider(type = RoleUserMapperProvider.class, method = "update")
    void dynamicUpdate(RoleUser roleUser);

    /**
     * 通过id删除
     *
     * @param id
     */
    @Delete("delete from " + TABLE_NAME + " where id=#{id}")
    void delete(Integer id);

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
                    INSERT_INTO(TABLE_NAME);
                    if (roleUser.getRoleId() != null)
                        VALUES("roleId", "#{roleId}");
                    if (roleUser.getUserId() != null)
                        VALUES("userId", "#{userId}");
                    if (roleUser.getCreateUser() != null)
                        VALUES("createUser", "#{createUser}");
                    if (roleUser.getUpdateUser() != null)
                        VALUES("updateUser", "#{updateUser}");
                    if (roleUser.getRemark() != null)
                        VALUES("remark", "#{remark}");
                    if (roleUser.getState() != null)
                        VALUES("state", "#{state}");
                    VALUES("createTime", "SYSDATE()");
                    VALUES("updateTime", "SYSDATE()");
                }
            }.toString();
        }

        public String update(RoleUser roleUser) {
            return new SQL() {
                {
                    UPDATE(TABLE_NAME);
                    if (roleUser.getUserId() != null)
                        SET("userId=#{userId}");
                    if (roleUser.getRoleId() != null)
                        SET("roleId=#{roleId}");
                    if (roleUser.getUpdateUser() != null)
                        SET("updateUser=#{updateUser}");
                    if (roleUser.getRemark() != null)
                        SET("remark=#{remark}");
                    if (roleUser.getState() != null)
                        SET("state=#{state}");
                    SET("updateTime=SYSDATE()");
                    WHERE("id=#{id}");

                }
            }.toString();
        }

        public String select(RoleUser roleUser) {
            return new SQL() {
                {
                    SELECT("id,userId,roleId,state");
                    FROM(TABLE_NAME);
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
