package pers.goetboy.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.goetboy.common.AbstractController;
import pers.goetboy.common.AbstractParam;
import pers.goetboy.common.exception.service.ServiceTipsException;
import pers.goetboy.common.EntityState;
import pers.goetboy.exam.model.entity.Exam;
import pers.goetboy.exam.model.entity.Question;
import pers.goetboy.exam.services.QuestionService;


/**
 * @author goetb
 */
@RestController
@RequestMapping("/question")
public class QuestionController extends AbstractController {
    private final
    QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    /**
     * 获取试题列表
     */
    @GetMapping("/list")
    public IPage<Exam> list(Integer current, Integer size) {
        return questionService.page(new Page(current, size));
    }

    /**
     * 获取试题信息
     *
     * @return 试题信息
     * @paramquestionId 试题id
     */
    @GetMapping(value = "/get")
    public Question get(Long questionId) {
        return questionService.get(questionId);
    }

    /**
     * 更新试题信息
     *
     * @param param 试题信息
     */
    @PostMapping(value = "/update")
    public void update(@RequestBody QuestionParam param) throws ServiceTipsException {
        questionService.update(param.getEntity());
    }

    @PostMapping(value = "/save")
    public void save(@RequestBody QuestionParam param) throws ServiceTipsException {
        questionService.save(param.getEntity());
    }

    /**
     * 删除试题信息
     *
     * @param param id 试题id
     */
    @PostMapping(value = "/delete")
    public void delete(@RequestBody QuestionParam param) {
        questionService.delete(param.getId());
    }

    /**
     * 更新试题状态
     *
     * @param param id 试题id | state  菜单状态 0停用 1正常
     * @throws ServiceTipsException 异常信息
     */
    @PostMapping(value = "/update/state")
    public void updateUserState(@RequestBody QuestionParam param) throws ServiceTipsException {
        questionService.updateState(param.getId(), EntityState.getByValue(param.getState()));
    }
}

@Data
@EqualsAndHashCode(callSuper = true)
class QuestionParam extends AbstractParam<Question> {

}