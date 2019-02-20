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
import pers.goetboy.exam.model.entity.Paper;
import pers.goetboy.exam.services.PaperService;


/**
 * @author goetb
 */
@RestController
@RequestMapping("/paper")
public class PaperController extends AbstractController {
    private final
    PaperService paperService;

    @Autowired
    public PaperController(PaperService paperService) {
        this.paperService = paperService;
    }

    /**
     * 获取试卷列表
     */
    @GetMapping("/list")
    public IPage<Exam> list(Integer current, Integer size) {
        return paperService.page(new Page(current, size));
    }

    /**
     * 获取试卷信息
     *
     * @return 试卷信息
     * @parampaperId 试卷id
     */
    @GetMapping(value = "/get")
    public Paper get(Long paperId) {
        return paperService.get(paperId);
    }

    /**
     * 更新试卷信息
     *
     * @param param 试卷信息
     */
    @PostMapping(value = "/update")
    public void update(@RequestBody PaperParam param) throws ServiceTipsException {
        paperService.update(param.getEntity());
    }

    @PostMapping(value = "/save")
    public void save(@RequestBody PaperParam param) throws ServiceTipsException {
        paperService.save(param.getEntity());
    }

    /**
     * 删除试卷信息
     *
     * @param param id 试卷id
     */
    @PostMapping(value = "/delete")
    public void delete(@RequestBody PaperParam param) {
        paperService.delete(param.getId());
    }

    /**
     * 更新试卷状态
     *
     * @param param id 试卷id | state  菜单状态 0停用 1正常
     * @throws ServiceTipsException 异常信息
     */
    @PostMapping(value = "/update/state")
    public void updateUserState(@RequestBody PaperParam param) throws ServiceTipsException {
        paperService.updateState(param.getId(), EntityState.getByValue(param.getState()));
    }
}

@Data
@EqualsAndHashCode(callSuper = true)
class PaperParam extends AbstractParam<Paper> {

}