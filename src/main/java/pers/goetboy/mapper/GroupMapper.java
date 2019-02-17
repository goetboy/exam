package pers.goetboy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import pers.goetboy.entity.sys.Group;

import java.util.List;

@Repository
public interface GroupMapper extends BaseMapper<Group> {

}
