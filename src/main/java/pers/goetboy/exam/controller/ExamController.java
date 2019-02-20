package pers.goetboy.exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.goetboy.common.AbstractController;
import pers.goetboy.common.AbstractParam;
import pers.goetboy.common.exception.service.ServiceTipsException;
import pers.goetboy.common.EntityState;
import pers.goetboy.exam.model.entity.Exam;
import pers.goetboy.exam.services.ExamService;


/**
 * @author goetb
 */
@RestController
@RequestMapping("/exam")
public class ExamController extends AbstractController {
    private final
    ExamService examService;

    @Autowired
    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    /**
     * 获取考试列表
     */
    @GetMapping("/list")
    public IPage<Exam> list(Integer current, Integer size) {
        return examService.page(new Page(current, size));
    }

    /**
     * 获取考试信息
     *
     * @param examId 考试id
     * @return 考试信息
     */
    @GetMapping(value = "/get")
    public Exam get(Long examId) {
        return examService.get(examId);
    }

    /**
     * 更新考试信息
     *
     * @param param 考试信息
     */
    @PostMapping(value = "/update")
    public void update(@RequestBody ExamParam param) throws ServiceTipsException {
        examService.update(param.getEntity());
    }

    @PostMapping(value = "/save")
    public void save(@RequestBody ExamParam param) throws ServiceTipsException {
        examService.save(param.getEntity());
    }

    /**
     * 删除考试信息
     *
     * @param param id 考试id
     */
    @PostMapping(value = "/delete")
    public void delete(@RequestBody ExamParam param) {
        examService.delete(param.getId());
    }

    /**
     * 更新考试状态
     *
     * @param param id 考试id | state  菜单状态 0停用 1正常
     * @throws ServiceTipsException 异常信息
     */
    @PostMapping(value = "/update/state")
    public void updateUserState(@RequestBody ExamParam param) throws ServiceTipsException {
        examService.updateState(param.getId(), EntityState.getByValue(param.getState()));
    }
}

@Data
class ExamParam extends AbstractParam<Exam> {

}