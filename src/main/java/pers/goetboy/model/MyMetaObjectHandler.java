package pers.goetboy.model;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pers.goetboy.entity.sys.User;

import java.util.Calendar;
import java.util.Date;

/**
 * 通用字段填充
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Object createdTime = metaObject.getValue("createdTime");
        if (createdTime == null) {
            metaObject.setValue("createdTime", new Date());
        }
        metaObject.setValue("createdUser", user.getId());

    }

    @Override
    public void updateFill(MetaObject metaObject) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        setFieldValByName("updatedTime", new Date(), metaObject);
        setFieldValByName("updatedUser", 1L, metaObject);
        /*

       AbstractEntity abstractEntity = (AbstractEntity) metaObject.getValue("param1");

        if(abstractEntity.getCreatedTime()==null)
        {
            abstractEntity.setUpdatedTime(new Date());
            metaObject.setValue("param1", abstractEntity);
        }*/
    }
}
