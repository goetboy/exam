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
import pers.goetboy.exam.model.entity.Examinee;
import pers.goetboy.exam.services.ExamineeService;


/**
 * @author goetb
 */
@RestController
@RequestMapping("/examinee")
public class ExamineeController extends AbstractController {
    private final
    ExamineeService examineeService;

    @Autowired
    public ExamineeController(ExamineeService examineeService) {
        this.examineeService = examineeService;
    }

    /**
     * 获取考生列表
     */
    @GetMapping("/list")
    public IPage<Exam> list(Integer current, Integer size) {
        return examineeService.page(new Page(current, size));
    }

    /**
     * 获取考生信息
     *
     * @return 考生信息
     * @paramexamineeId 考生id
     */
    @GetMapping(value = "/get")
    public Examinee get(Long examineeId) {
        return examineeService.get(examineeId);
    }

    /**
     * 更新考生信息
     *
     * @param param 考生信息
     */
    @PostMapping(value = "/update")
    public void update(@RequestBody ExamineeParam param) throws ServiceTipsException {
        examineeService.update(param.getEntity());
    }

    @PostMapping(value = "/save")
    public void save(@RequestBody ExamineeParam param) throws ServiceTipsException {
        examineeService.save(param.getEntity());
    }

    /**
     * 删除考生信息
     *
     * @param param id 考生id
     */
    @PostMapping(value = "/delete")
    public void delete(@RequestBody ExamineeParam param) {
        examineeService.delete(param.getId());
    }

    /**
     * 更新考生状态
     *
     * @param param id 考生id | state  菜单状态 0停用 1正常
     * @throws ServiceTipsException 异常信息
     */
    @PostMapping(value = "/update/state")
    public void updateUserState(@RequestBody ExamineeParam param) throws ServiceTipsException {
        examineeService.updateState(param.getId(), EntityState.getByValue(param.getState()));
    }
}

@Data
@EqualsAndHashCode(callSuper = true)
class ExamineeParam extends AbstractParam<Examinee> {

}