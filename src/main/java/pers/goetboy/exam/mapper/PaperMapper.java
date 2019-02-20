package pers.goetboy.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import pers.goetboy.exam.model.entity.Paper;

/**
 * 试卷
 */
@Repository
public interface PaperMapper extends BaseMapper<Paper> {
}
