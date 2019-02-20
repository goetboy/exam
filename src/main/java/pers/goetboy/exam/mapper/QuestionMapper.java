package pers.goetboy.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import pers.goetboy.exam.model.entity.Question;

/**
 * 问题mapper
 *
 * @author:goetb
 * @date 2019 /02 /13
 **/
@Repository
public interface QuestionMapper extends BaseMapper<Question> {

}
