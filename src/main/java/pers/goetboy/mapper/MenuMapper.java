package pers.goetboy.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;
import pers.goetboy.entity.sys.*;

import java.util.List;

@Repository
public interface MenuMapper {


    @Select("select * from " + Menu.TABLE_NAME)
    List<Menu> getAll();

    @Select("select * from " + Menu.TABLE_NAME + " where id=#{id}")
    Menu get(Long id);


    //void insert(Role role);
    @InsertProvider(type = MenuMapperProvider.class, method = "insert")
    Long dynamicInsert(Menu menu);

    //void update(Role role);

    /**
     * 根据传入条件更新
     *
     * @param menu
     */
    @UpdateProvider(type = MenuMapperProvider.class, method = "update")
    void dynamicUpdate(Menu menu);

    /**
     * 通过用户名获取菜单权限
     *
     * @param userName
     * @return
     */
    @Select("select * from " + Menu.TABLE_NAME + " m, " + RoleMenu.TABLE_NAME + " rm ," + UserRole.TABLE_NAME + " ru," + User.TABLE_NAME + " u where m.id=rm.MENU_ID and rm.ROLE_ID=ru.ROLE_ID and ru.USER_ID=u.id and u.USERNAME = #{userName}")
    List<Menu> listByUserName(String userName);

    /**
     * 通过角色名称获取菜单
     *
     * @param roleName
     * @return
     */
    @Select("select * from " + Menu.TABLE_NAME + " m, " + RoleMenu.TABLE_NAME + " rm ," + Role.TABLE_NAME + " r where  m.id=rm.MENU_ID and rm.ROLE_ID = r.id and r.NAME = #{roleName}")
    List<Menu> listByRoleName(String roleName);

    /**
     * 通过id删除菜单
     *
     * @param id
     */
    @Delete("delete from " + Menu.TABLE_NAME + " where id=#{id}")
    void delete(Long id);

    class MenuMapperProvider {

        /**
         * 插入动态语句
         *
         * @param menu
         * @return
         */
        public String insert(Menu menu) {
            return new SQL() {
                {
                    INSERT_INTO(Menu.TABLE_NAME);
                    if (menu.getName() != null)
                        VALUES("name", "#{name}");
                    if (menu.getParentId() != null)
                        VALUES("parent_id", "#{parentId}");
                    if (menu.getSort() != null)
                        VALUES("sort", "#{sort}");
                    if (menu.getType() != null)
                        VALUES("type", "#{type}");
                    if (menu.getUrl() != null)
                        VALUES("url", "#{url}");
                    if (menu.getCreatedUser() != null)
                        VALUES("created_User", "#{createdUser}");
                    if (menu.getUpdatedUser() != null)
                        VALUES("updated_User", "#{updatedUser}");
                    if (menu.getRemark() != null)
                        VALUES("remark", "#{remark}");
                    if (menu.getState() != null)
                        VALUES("state", "#{state}");
                    VALUES("created_Time", "SYSDATE");
                    VALUES("updated_Time", "SYSDATE");
                }
            }.toString();
        }

        public String update(Menu menu) {
            return new SQL() {
                {
                    UPDATE(Menu.TABLE_NAME);
                    if (menu.getName() != null)
                        SET("name=#{name}");
                    if (menu.getParentId() != null)
                        SET("parent_id=#{parentId}");
                    if (menu.getSort() != null)
                        SET("sort=#{sort}");
                    if (menu.getType() != null)
                        SET("type=#{type}");
                    if (menu.getUrl() != null)
                        SET("url=#{url}");
                    if (menu.getUpdatedUser() != null)
                        SET("updated_User=#{updatedUser}");
                    if (menu.getRemark() != null)
                        SET("remark=#{remark}");
                    if (menu.getState() != null)
                        SET("state=#{state}");
                    SET("updated_Time=SYSDATE");
                    WHERE("id=#{id}");

                }
            }.toString();
        }

        public String select(Menu menu) {
            return new SQL() {
                {
                    SELECT("id,name,url,type,parent_id,sort,state");
                    FROM(Menu.TABLE_NAME);
                    if (menu.getId() != null)
                        WHERE("id=#{id}");
                    if (menu.getName() != null)
                        WHERE("name like #{name}");
                }
            }.toString();
        }
    }
}
