package pers.goetboy.exam.services;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.goetboy.common.AbstractService;
import pers.goetboy.exam.mapper.AnswerMapper;
import pers.goetboy.exam.mapper.QuestionMapper;
import pers.goetboy.exam.model.entity.Answer;
import pers.goetboy.exam.model.entity.Question;

import java.util.List;

/**
 * 考生service
 *
 * @author:goetb
 * @date 2019 /02 /13
 **/
@Service
public class QuestionService extends AbstractService<Question> {
    private final QuestionMapper questionMapper;
    private final AnswerMapper answerMapper;

    /**
     * 构造函数
     *
     * @param questionMapper 主要mapper
     * @param answerMapper   答案mapper
     */
    @Autowired
    public QuestionService(QuestionMapper questionMapper, AnswerMapper answerMapper) {
        super(questionMapper);
        this.questionMapper = questionMapper;
        this.answerMapper = answerMapper;
    }

    /**
     * 存储问题和答案
     *
     * @param question 问题
     * @param answers  答案列表
     * @return 问题id
     */
    public Long save(Question question, List<Answer> answers) {
        super.save(question);
        if (CollectionUtils.isNotEmpty(answers)) {
            answers.forEach(answer -> {
                answer.setQuestionId(question.getId());
                answerMapper.insert(answer);
            });
        }
        return question.getId();
    }

    /**
     * 删除问题
     * @param id 实体类id
     */
    @Override
    public void delete(Long id) {
        Question question = questionMapper.selectById(id);
        if (question != null) {
            answerMapper.delete(Wrappers.<Answer>lambdaQuery().eq(Answer::getQuestionId, id));
            questionMapper.deleteById(id);
        }
    }

    /**
     * 获取问题信息，含答案列表
     *
     * @param id id
     * @return
     */
    @Override
    public Question get(Long id) {
        Question question = super.get(id);
        List<Answer> answers = answerMapper.selectList(Wrappers.<Answer>lambdaQuery().eq(Answer::getQuestionId, id));
        question.setAnswers(answers);
        return question;
    }
}
