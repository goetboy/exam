package pers.goetboy.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;
import pers.goetboy.entity.sys.User;

import java.util.List;

/**
 * 用户管理mapper
 */
@Repository
public interface UserMapper {
    String TABLE_NAME = User.TABLE_NAME;

    @Select("select * from " + TABLE_NAME)
    List<User> getAll();

    @Select("select * from " + TABLE_NAME + " where id=#{id}")
    User get(Long id);

    // @Insert("insert  into User( userName, password, createUser, createTime, updateTime, updateUser) values (#{userName},#{password},#{createUser},#{createTime},#{updateTime},#{updateUser})")
    //void insert(User user);

    /**
     * 动态插入
     *
     * @param user
     */
    @InsertProvider(type = UserMapperProvider.class, method = "insert")
    Long dynamicInsert(User user);

    /**
     * 动态更新
     *
     * @param user
     */
    @UpdateProvider(type = UserMapperProvider.class, method = "update")
    void dynamicUpdate(User user);
    // @Update("update User set userName=#{userName},password=#{password},updateTime=#{updateTime},updateUser=#{updateUser} where id=#{id}")
    //void update(User user);

    @Delete("delete from " + TABLE_NAME + " where id=#{id}")
    void delete(Long id);
    @Select("select id,userName,password,state from " + TABLE_NAME + " where username=#{username}")
    User  findByUsername(String username);



    class UserMapperProvider {
        /**
         * 插入动态语句
         *
         * @param user
         * @return
         */
        public String insert(User user) {
            return new SQL() {
                {
                    INSERT_INTO(TABLE_NAME);
                    if (user.getUsername() != null)
                        VALUES("username", "#{username}");
                    if (user.getPassword() != null)
                        VALUES("password", "#{password}");
                 if (user.getNickName() != null)
                        VALUES("nickName", "#{nickName}");
                    if (user.getCreatedUser() != null)
                        VALUES("created_User", "#{createdUser}");
                    if (user.getUpdatedUser() != null)
                        VALUES("updated_User", "#{updatedUser}");
                    if (user.getRemark() != null)
                        VALUES("remark", "#{remark}");
                    if (user.getState() != null)
                        VALUES("state", "#{state}");
                    VALUES("updated_Time", "SYSDATE");
                    VALUES("created_Time", "SYSDATE");
                }
            }.toString();
        }

        public String update(User user) {
            return new SQL() {
                {
                    UPDATE(TABLE_NAME);
                    if (user.getUsername() != null)
                        SET("username=#{username}");
                    if (user.getPassword() != null)
                        SET("password=#{password}");
                   /* if (user.getNickName() != null)
                        SET("nickName=#{nickName}");*/
                    if (user.getUpdatedUser() != null)
                        SET("updated_User=#{updatedUser}");
                    if (user.getRemark() != null)
                        SET("remark=#{remark}");
                    if (user.getState() != null)
                        SET("state=#{state}");
                    SET("updated_Time=SYSDATE");
                    WHERE("id=#{id}");
                }
            }.toString();
        }

        protected String select(User user) {
            return new SQL() {
                {
                    SELECT("id,userName,password,nickName,state");
                    FROM(TABLE_NAME);
                    if (user.getId() != null)
                        WHERE("id=#{id}");
                    /**
                     * 如果用户名为空密码不为空
                     */
                    if (user.getUsername() != null && user.getPassword() == null)
                        WHERE("username like '%${username}$'");
                    //如果用户名和密码都不为空
                    if (user.getUsername() != null && user.getPassword() != null) {
                        WHERE("username = #{username}");
                        WHERE("password = #{password}");
                    }
               /*     if (user.getNickName() != null) {
                        WHERE("nickName like '%${nickName}'%");


                    }*/
                }
            }.toString();
        }
    }
}
