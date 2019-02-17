package pers.goetboy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pers.goetboy.common.AbstractParam;
import pers.goetboy.common.AbstractController;
import pers.goetboy.common.exception.service.ServiceTipsException;
import pers.goetboy.entity.EntityState;
import pers.goetboy.entity.sys.Group;
import pers.goetboy.services.GroupService;

/**
 * 分组管理
 *
 * @author:goetb
 * @date 2019 /02 /13
 **/
@RestController("/group")
public class GroupController extends AbstractController {
    @Autowired
    private GroupService groupService;

    /**
     * 获取分组列表
     */
    @GetMapping("/list")
    public IPage<Group> list( Integer current, Integer size) {
        return groupService.page(new Page(current, size));
    }

    @GetMapping("/list/{id}")
    public IPage<Group> listById(Integer current, Integer size) {
        return groupService.page(new Page(current, size));
    }

    /**
     * 获取分组信息
     *
     * @param id 分组id
     * @return 菜单信息
     */
    @GetMapping(value = "/get")
    public Group get(Long id) {
        return groupService.get(id);
    }

    /**
     * 更新分组信息
     *
     * @param param 分组信息
     */
    @PostMapping(value = "/update")
    public void update(@RequestBody GroupParam
                               param) throws
            ServiceTipsException {
        groupService.update(param.getEntity());
    }

    /**
     * 存储分组信息
     *
     * @param param entity 分组对象
     * @throws ServiceTipsException
     */
    @PostMapping(value = "/save")
    public void save(@RequestBody GroupParam param) throws ServiceTipsException {
        groupService.save(param.getEntity());
    }

    /**
     * 删除分组信息
     *
     * @param param id 分组id
     */
    @PostMapping(value = "/delete")
    public void delete(@RequestBody GroupParam param) {
        groupService.delete(param.getId());
    }

    /**
     * 更新分组状态
     *
     * @param param id 分组id | state  状态 0停用|1正常
     * @throws ServiceTipsException 异常信息
     */
    @PostMapping(value = "/update/state")
    public void updateUserState(@RequestBody GroupParam param) throws ServiceTipsException {
        groupService.updateState(param.getId(), EntityState.getByValue(param.getState()));
    }

    @Data
    class GroupParam extends AbstractParam<Group> {

    }
}
