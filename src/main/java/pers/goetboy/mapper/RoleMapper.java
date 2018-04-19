package pers.goetboy.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;
import pers.goetboy.entity.sys.Role;

import java.util.List;

@Repository
public interface RoleMapper {
    static  final String TABLE_NAME = Role.TABLE_NAME;

    @Select("select * from " + TABLE_NAME)
    List<Role> getAll();

    @Select("select * from " + TABLE_NAME + " where id=#{id}")
    Role get(Integer id);


    //void insert(Role role);
    @InsertProvider(type = RoleMapperProvider.class, method = "insert")
    void dynamicInsert(Role role);

    //void update(Role role);

    /**
     * 根据传入条件更新
     *
     * @param role
     */
    @UpdateProvider(type = RoleMapperProvider.class, method = "update")
    void dynamicUpdate(Role role);

    /**
     * 通过id删除
     *
     * @param id
     */
    @Delete("delete from " + TABLE_NAME + " where id=#{id}")
    void delete(Integer id);

    class RoleMapperProvider {
        /**
         * 插入动态语句
         *
         * @param role
         * @return
         */
        public String insert(Role role) {
            return new SQL() {
                {
                    INSERT_INTO(TABLE_NAME);
                    if (role.getName() != null)
                        VALUES("name", "#{name}");
                    if (role.getCreateUser() != null)
                        VALUES("createUser", "#{createUser}");
                    if (role.getUpdateUser() != null)
                        VALUES("updateUser", "#{updateUser}");
                    if (role.getRemark() != null)
                        VALUES("remark", "#{remark}");
                    if (role.getState() != null)
                        VALUES("state", "#{state}");
                    VALUES("createTime", "SYSDATE()");
                    VALUES("updateTime", "SYSDATE()");
                }
            }.toString();
        }

        public String update(Role role) {
            return new SQL() {
                {
                    UPDATE(TABLE_NAME);
                    if (role.getName() != null)
                        SET("name=#{name}");
                    if (role.getUpdateUser() != null)
                        SET("updateUser=#{updateUser}");
                    if (role.getRemark() != null)
                        SET("remark=#{remark}");
                    if (role.getState() != null)
                        SET("state=#{state}");
                    SET("updateTime=SYSDATE()");
                    WHERE("id=#{id}");

                }
            }.toString();
        }

        public String select(Role role) {
            return new SQL() {
                {
                    SELECT("id,name,state");
                    FROM(TABLE_NAME);
                    if (role.getId() != null)
                        WHERE("id=#{id}");
                    if (role.getName() != null)
                        WHERE("name like #{name}");
                }
            }.toString();
        }
    }
}
