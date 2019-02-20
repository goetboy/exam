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
import pers.goetboy.exam.model.entity.Answer;
import pers.goetboy.exam.services.AnswerService;


/**
 * @author goetb
 */
@RestController
@RequestMapping("/answer")
public class AnswerController extends AbstractController {
    private final
    AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    /**
     * 获取答案列表
     */
    @GetMapping("/list")
    public IPage<Exam> list(Integer current, Integer size) {
        return answerService.page(new Page(current, size));
    }

    /**
     * 获取答案信息
     *
     * @return 答案信息
     * @paramanswerId 答案id
     */
    @GetMapping(value = "/get")
    public Answer get(Long answerId) {
        return answerService.get(answerId);
    }

    /**
     * 更新答案信息
     *
     * @param param 答案信息
     */
    @PostMapping(value = "/update")
    public void update(@RequestBody AnswerParam param) throws ServiceTipsException {
        answerService.update(param.getEntity());
    }

    @PostMapping(value = "/save")
    public void save(@RequestBody AnswerParam param) throws ServiceTipsException {
        answerService.save(param.getEntity());
    }

    /**
     * 删除答案信息
     *
     * @param param id 答案id
     */
    @PostMapping(value = "/delete")
    public void delete(@RequestBody AnswerParam param) {
        answerService.delete(param.getId());
    }

    /**
     * 更新答案状态
     *
     * @param param id 答案id | state  菜单状态 0停用 1正常
     * @throws ServiceTipsException 异常信息
     */
    @PostMapping(value = "/update/state")
    public void updateUserState(@RequestBody AnswerParam param) throws ServiceTipsException {
        answerService.updateState(param.getId(), EntityState.getByValue(param.getState()));
    }
}

@Data
@EqualsAndHashCode(callSuper = true)
class AnswerParam extends AbstractParam<Answer> {

}