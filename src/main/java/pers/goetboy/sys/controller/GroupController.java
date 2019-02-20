package pers.goetboy.sys.controller;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.goetboy.common.AbstractController;
import pers.goetboy.common.AbstractParam;
import pers.goetboy.common.EntityState;
import pers.goetboy.common.Tree;
import pers.goetboy.common.exception.service.ServiceTipsException;
import pers.goetboy.sys.model.entity.Group;
import pers.goetboy.sys.services.GroupService;

import java.util.List;

/**
 * 分组管理
 *
 * @author:goetb
 * @date 2019 /02 /13
 **/
@RestController
@RequestMapping("/group")
public class GroupController extends AbstractController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    /**
     * 获取分组列表
     */
    @GetMapping("/list")
    public Tree<Group> list(Long parentId) {
        return groupService.listGroupTree(parentId);
    }

    @GetMapping("/list/{parentId}")
    public List<Group> listTreeByParentId(@PathVariable Long parentId) {
        return groupService.listByParentId(parentId);
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
     * @throws ServiceTipsException 业务异常
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

}

@EqualsAndHashCode(callSuper = true)
@Data
class GroupParam extends AbstractParam<Group> {

}
