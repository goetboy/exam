package pers.goetboy.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
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
    @Select("select * from " + Menu.TABLE_NAME + " m, " + RoleMenu.TABLE_NAME + " rm ," + RoleUser.TABLE_NAME + " ru," + User.TABLE_NAME + " u where m.id=rm.MENU_ID and rm.ROLE_ID=ru.ROLE_ID and ru.USER_ID=u.id and u.USERNAME = #{userName}")
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
                    if (menu.getParent() != null)
                        VALUES("parent", "#{parent}");
                    if (menu.getSort() != null)
                        VALUES("sort", "#{sort}");
                    if (menu.getType() != null)
                        VALUES("type", "#{type}");
                    if (menu.getUrl() != null)
                        VALUES("url", "#{url}");
                    if (menu.getCreateUser() != null)
                        VALUES("createUser", "#{createUser}");
                    if (menu.getUpdateUser() != null)
                        VALUES("updateUser", "#{updateUser}");
                    if (menu.getRemark() != null)
                        VALUES("remark", "#{remark}");
                    if (menu.getState() != null)
                        VALUES("state", "#{state}");
                    VALUES("createTime", "SYSDATE");
                    VALUES("updateTime", "SYSDATE");
                }
            }.toString();
        }

        public String update(Menu menu) {
            return new SQL() {
                {
                    UPDATE(Menu.TABLE_NAME);
                    if (menu.getName() != null)
                        SET("name=#{name}");
                    if (menu.getParent() != null)
                        SET("parent=#{parent}");
                    if (menu.getSort() != null)
                        SET("sort=#{sort}");
                    if (menu.getType() != null)
                        SET("type=#{type}");
                    if (menu.getUrl() != null)
                        SET("url=#{url}");
                    if (menu.getUpdateUser() != null)
                        SET("updateUser=#{updateUser}");
                    if (menu.getRemark() != null)
                        SET("remark=#{remark}");
                    if (menu.getState() != null)
                        SET("state=#{state}");
                    SET("updateTime=SYSDATE");
                    WHERE("id=#{id}");

                }
            }.toString();
        }

        public String select(Menu menu) {
            return new SQL() {
                {
                    SELECT("id,name,url,type,parent,sort,state");
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
