package pers.goetboy.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import pers.goetboy.sys.model.entity.Group;

import java.util.List;

/**
 * 分组查询mapper
 *
 * @author goetb
 */
@Repository
public interface GroupMapper extends BaseMapper<Group> {
    /**
     * 通过parentid查询节点和子节点
     *
     * @param parentId
     * @return
     */
    @Select("select * from sys_group start with id=#{arg0} connect by(prior id) = #{arg0}")
    public List<Group> selectTreeByParentId(Long parentId);
}
