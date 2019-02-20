package pers.goetboy.exam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.goetboy.common.AbstractService;
import pers.goetboy.exam.mapper.QuestionMapper;
import pers.goetboy.exam.model.entity.Question;

/**
 * 考生service
 *
 * @author:goetb
 * @date 2019 /02 /13
 **/
@Service
public class QuestionService extends AbstractService<Question> {
    private final QuestionMapper questionMapper;

    /**
     * 构造函数
     *
     * @param questionMapper 主要mapper
     */
    @Autowired
    public QuestionService(QuestionMapper questionMapper) {
        super(questionMapper);
        this.questionMapper = questionMapper;

    }
}
