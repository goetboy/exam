package pers.goetboy.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;
import pers.goetboy.entity.sys.Role;
import pers.goetboy.entity.sys.RoleMenu;
import pers.goetboy.entity.sys.UserRole;

import java.util.List;

@Repository
public interface RoleMapper {

    @Select("select * from " + Role.TABLE_NAME)
    List<Role> getAll();

    @Select("select * from " + Role.TABLE_NAME + " where id=#{id}")
    Role get(Long id);
    @Select("select * from " + Role.TABLE_NAME + " where name=#{name}")
    Role getByName(String name);

    //void insert(Role role);
    @InsertProvider(type = RoleMapperProvider.class, method = "insert")
   public Long dynamicInsert(Role role);

    //void update(Role role);

    /**
     * 根据传入条件更新
     *
     * @param role
     */
    @UpdateProvider(type = RoleMapperProvider.class, method = "update")
    public int dynamicUpdate(Role role);

    /**
     * 通过id删除
     *
     * @param id
     */
    @Delete("delete from " + Role.TABLE_NAME + " where id=#{id}")
    public void delete(Long id);
    @Select("select t.name from "+Role.TABLE_NAME+" t where t.id in (select t1.role_id from "+ UserRole.TABLE_NAME+ " t1 where  t1.user_id=#{userId}) ")
    public List<Role> findByUserId(Long userId);

    /**
     * 通过菜单查角色
     * @param menuId
     * @return
     */
    @Select("select t.name from "+Role.TABLE_NAME+" t where t.id in (select t1.role_id from "+ RoleMenu.TABLE_NAME+ " t1 where  t1.menu_id=#{menuId}) ")
    public List<Role> findByMenuId(Long menuId);
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
                    INSERT_INTO(Role.TABLE_NAME);
                    if (role.getName() != null)
                        VALUES("name", "#{name}");
                    if (role.getCreatedUser() != null)
                        VALUES("created_User", "#{createdUser}");
                    if (role.getUpdatedUser() != null)
                        VALUES("updated_User", "#{updatedUser}");
                    if (role.getRemark() != null)
                        VALUES("remark", "#{remark}");
                    if (role.getState() != null)
                        VALUES("state", "#{state}");
                    VALUES("created_Time", "SYSDATE");
                    VALUES("updated_Time", "SYSDATE");
                }
            }.toString();
        }

        public String update(Role role) {
            return new SQL() {
                {
                    UPDATE(Role.TABLE_NAME);
                    if (role.getName() != null)
                        SET("name=#{name}");
                    if (role.getUpdatedUser() != null)
                        SET("updatedUser=#{updatedUser}");
                    if (role.getRemark() != null)
                        SET("remark=#{remark}");
                    if (role.getState() != null)
                        SET("state=#{state}");
                    SET("updated_Time=SYSDATE");
                    WHERE("id=#{id}");

                }
            }.toString();
        }

        public String select(Role role) {
            return new SQL() {
                {
                    SELECT("id,name,state");
                    FROM(Role.TABLE_NAME);
                    if (role.getId() != null)
                        WHERE("id=#{id}");
                    if (role.getName() != null)
                        WHERE("name like #{name}");
                }
            }.toString();
        }
    }
}
