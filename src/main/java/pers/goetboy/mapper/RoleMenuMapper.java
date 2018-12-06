package pers.goetboy.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;
import pers.goetboy.entity.sys.Menu;
import pers.goetboy.entity.sys.Role;
import pers.goetboy.entity.sys.RoleMenu;

import java.util.List;

@Repository
public interface RoleMenuMapper {

    /**
     * 查询角色菜单关系
     * @return
     */
    @Select("select * from " + RoleMenu.TABLE_NAME)
    public List<RoleMenu> getAll();

    /**
     * 通过id查询角色菜单关系
     * @param id
     * @return
     */
    @Select("select * from " + RoleMenu.TABLE_NAME + " where id=#{id}")
    public RoleMenu get(Long id);


    /**
     * 动态更新菜单角色关系对象
     * @param roleMenu
     */
    @InsertProvider(type = RoleMenuMapperProvider.class, method = "insert")
    public Long dynamicInsert(RoleMenu roleMenu);

    /**
     * 删除角色对应的菜单关系
     * @param id
     */
    @Delete("delete " + RoleMenu.TABLE_NAME + " where role_id in (select id from " + Role.TABLE_NAME + " where id=#{id} ) ")
    public void deleteByRoleId(Long id);
    @Delete("delete " + RoleMenu.TABLE_NAME + " where menu_id in (select id from " + Menu.TABLE_NAME + " where id=#{id} ) ")
    public void deleteByMenuId(Long id);
    /**
     * 根据传入条件更新
     *
     * @param roleMenu
     */
    @UpdateProvider(type = RoleMenuMapperProvider.class, method = "update")
    public void dynamicUpdate(RoleMenu roleMenu);

    /**
     * 通过id删除
     *
     * @param id
     */
    @Delete("delete from " + RoleMenu.TABLE_NAME + " where id=#{id}")
    public void delete(Long id);

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
                    INSERT_INTO(RoleMenu.TABLE_NAME);
                    if (roleMenu.getRoleId() != null)
                        VALUES("roleId", "#{roleId}");
                    if (roleMenu.getMenuId() != null)
                        VALUES("menuId", "#{menuId}");
                    if (roleMenu.getCreatedUser() != null)
                        VALUES("created_User", "#{createdUser}");
                    if (roleMenu.getUpdatedUser() != null)
                        VALUES("updated_User", "#{updatedUser}");
                    if (roleMenu.getRemark() != null)
                        VALUES("remark", "#{remark}");
                    if (roleMenu.getState() != null)
                        VALUES("state", "#{state}");
                    VALUES("created_Time", "SYSDATE");
                    VALUES("updated_Time", "SYSDATE");
                }
            }.toString();
        }

        public String update(RoleMenu roleMenu) {
            return new SQL() {
                {
                    UPDATE(RoleMenu.TABLE_NAME);
                    if (roleMenu.getMenuId() != null)
                        SET("menuId=#{menuId}");
                    if (roleMenu.getRoleId() != null)
                        SET("roleId=#{roleId}");
                    if (roleMenu.getUpdatedUser() != null)
                        SET("updated_User=#{updatedUser}");
                    if (roleMenu.getRemark() != null)
                        SET("remark=#{remark}");
                    if (roleMenu.getState() != null)
                        SET("state=#{state}");
                    SET("updated_Time=SYSDATE");
                    WHERE("id=#{id}");

                }
            }.toString();
        }

        public String select(RoleMenu roleMenu) {
            return new SQL() {
                {
                    SELECT("id,menuId,roleId,state");
                    FROM(RoleMenu.TABLE_NAME);
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
