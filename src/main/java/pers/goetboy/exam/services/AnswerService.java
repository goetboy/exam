package pers.goetboy.exam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.goetboy.common.AbstractService;
import pers.goetboy.exam.mapper.AnswerMapper;
import pers.goetboy.exam.model.entity.Answer;

/**
 * 答案service
 *
 * @author:goetb
 * @date 2019 /02 /13
 **/
@Service
public class AnswerService extends AbstractService<Answer> {
    private final AnswerMapper answerMapper;

    /**
     * 构造函数
     *
     * @param answerMapper 主要mapper
     */
    @Autowired
    public AnswerService(AnswerMapper answerMapper) {
        super(answerMapper);
        this.answerMapper = answerMapper;

    }
}
