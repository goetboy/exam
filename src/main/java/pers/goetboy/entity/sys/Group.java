package pers.goetboy.entity.sys;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pers.goetboy.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * 角色表
 * @author goetb
 */
@Entity
@Table(name = Group.TABLE_NAME)
@Getter
@Setter
@ToString(callSuper = true)
public class Group extends AbstractEntity implements Serializable {
    public  final  static String  TABLE_NAME = "sys_group";
    @
    private  Integer parentId;

}
