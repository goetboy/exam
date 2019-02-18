package pers.goetboy.controller;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.goetboy.common.AbstractController;
import pers.goetboy.common.AbstractParam;
import pers.goetboy.common.exception.service.ServiceTipsException;
import pers.goetboy.entity.EntityState;
import pers.goetboy.entity.sys.Dict;
import pers.goetboy.services.DictService;

import java.util.List;

/**
 * 分组管理
 *
 * @author:goetb
 * @date 2019 /02 /13
 **/
@RestController("/dict")
public class DictController extends AbstractController {
    private final DictService dictService;

    @Autowired
    public DictController(DictService dictService) {
        this.dictService = dictService;
    }


    @GetMapping("/list/{parentCode}")
    public List<Dict> listTreeByParentCode(@PathVariable String parentCode) {
        return dictService.listByParentCode(parentCode);
    }

    /**
     * 获取分组信息
     *
     * @param id 分组id
     * @return 菜单信息
     */
    @GetMapping(value = "/get")
    public Dict get(Long id) {
        return dictService.get(id);
    }

    /**
     * 通过code查询字典对象
     *
     * @param code 字典编码
     * @return 字典对象
     */
    @GetMapping(value = "/get/code")
    public Dict getByCode(String code) {
        return dictService.getByCode(code);
    }

    /**
     * 更新分组信息
     *
     * @param param 分组信息
     */
    @PostMapping(value = "/update")
    public void update(@RequestBody DictParam
                               param) throws
            ServiceTipsException {
        dictService.update(param.getEntity());
    }

    /**
     * 存储字典信息
     *
     * @param param entity 字典对象
     * @throws ServiceTipsException 业务异常
     */
    @PostMapping(value = "/save")
    public void save(@RequestBody DictParam param) throws ServiceTipsException {
        dictService.save(param.getEntity());
    }

    /**
     * 删除字典信息
     *
     * @param param id 字典id
     */
    @PostMapping(value = "/delete")
    public void delete(@RequestBody DictParam param) {
        dictService.delete(param.getId());
    }

    /**
     * 更新字典状态
     *
     * @param param id 字典id | state  状态 0停用|1正常
     * @throws ServiceTipsException 异常信息
     */
    @PostMapping(value = "/update/state")
    public void updateUserState(@RequestBody DictParam param) throws ServiceTipsException {
        dictService.updateState(param.getId(), EntityState.getByValue(param.getState()));
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    class DictParam extends AbstractParam<Dict> {
    }
}
