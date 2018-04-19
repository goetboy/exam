package pers.goetboy.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;
import pers.goetboy.entity.sys.Role;
import pers.goetboy.entity.sys.RoleMenu;
import pers.goetboy.entity.sys.RoleUser;

import java.util.List;

@Repository
public interface RoleMenuMapper {

  static final String TABLE_NAME = RoleMenu.TABLE_NAME;

    @Select("select * from " + TABLE_NAME)
    List<RoleMenu> getAll();

    @Select("select * from " + TABLE_NAME + " where id=#{id}")
    RoleMenu get(Integer id);


    //void insert(Role role);
    @InsertProvider(type = RoleMenuMapperProvider.class, method = "insert")
    void dynamicInsert(RoleMenu roleMenu);

    //void update(Role role);

    /**
     * 根据传入条件更新
     *
     * @param roleMenu
     */
    @UpdateProvider(type = RoleMenuMapperProvider.class, method = "update")
    void dynamicUpdate(RoleMenu roleMenu);

    /**
     * 通过id删除
     *
     * @param id
     */
    @Delete("delete from " + TABLE_NAME + " where id=#{id}")
    void delete(Integer id);

    class RoleMenuMapperProvider {
        /**
         * 插入动态语句
         *
         * @param roleMenu
         * @return
         */
        public String insert(RoleMenu roleMenu) {
            return new SQL() {
                {
                    INSERT_INTO(TABLE_NAME);
                    if (roleMenu.getRoleId() != null)
                        VALUES("roleId", "#{roleId}");
                    if (roleMenu.getMenuId() != null)
                        VALUES("menuId", "#{menuId}");
                    if (roleMenu.getCreateUser() != null)
                        VALUES("createUser", "#{createUser}");
                    if (roleMenu.getUpdateUser() != null)
                        VALUES("updateUser", "#{updateUser}");
                    if (roleMenu.getRemark() != null)
                        VALUES("remark", "#{remark}");
                    if (roleMenu.getState() != null)
                        VALUES("state", "#{state}");
                    VALUES("createTime", "SYSDATE()");
                    VALUES("updateTime", "SYSDATE()");
                }
            }.toString();
        }

        public String update(RoleMenu roleMenu) {
            return new SQL() {
                {
                    UPDATE(TABLE_NAME);
                    if (roleMenu.getMenuId() != null)
                        SET("menuId=#{menuId}");
                    if (roleMenu.getRoleId() != null)
                        SET("roleId=#{roleId}");
                    if (roleMenu.getUpdateUser() != null)
                        SET("updateUser=#{updateUser}");
                    if (roleMenu.getRemark() != null)
                        SET("remark=#{remark}");
                    if (roleMenu.getState() != null)
                        SET("state=#{state}");
                    SET("updateTime=SYSDATE()");
                    WHERE("id=#{id}");

                }
            }.toString();
        }

        public String select(RoleMenu roleMenu) {
            return new SQL() {
                {
                    SELECT("id,menuId,roleId,state");
                    FROM(TABLE_NAME);
                    if (roleMenu.getId() != null)
                        WHERE("id=#{id}");
                    if (roleMenu.getMenuId() != null)
                        WHERE("menuId=#{menuId}");
                    if (roleMenu.getRoleId() != null)
                        WHERE("roleId=#{roleId}");
                }
            }.toString();
        }
    }
}
