package pers.goetboy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.goetboy.common.AbstractController;
import pers.goetboy.common.AbstractParam;
import pers.goetboy.common.exception.service.ServiceTipsException;
import pers.goetboy.entity.EntityState;
import pers.goetboy.entity.sys.Role;
import pers.goetboy.entity.sys.User;
import pers.goetboy.services.UserService;

import java.util.List;

/**
 * @author goetb
 */
@RestController()
@RequestMapping("/user")
public class UserController extends AbstractController {
    private final
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 获取用户列表
     */
    @GetMapping("/list")
    public IPage<User> list(Integer current, Integer size) {
        return userService.page(new Page(current, size));
    }

    /**
     * 获取用户信息
     *
     * @param userId 用户id
     * @return 用户信息
     */
    @GetMapping(value = "/get")
    public User get(Long userId) {
        return userService.get(userId);
    }

    /**
     * 更新用户信息
     * 可更新 nickName 字段
     * 可更新 remark字段
     *
     * @param param 使用 {@link UserParam#getEntity()}}用户信息
     */
    @PostMapping(value = "/update")
    public void update(@RequestBody UserParam param) throws ServiceTipsException {
        userService.update(param.getEntity());
    }

    /**
     * 删除用户信息
     *
     * @param param 使用 {@link UserParam#id} 用户id
     */
    @PostMapping(value = "/delete")
    public void delete(@RequestBody UserParam param) {
        userService.delete(param.getId());
    }

    /**
     * 更新用户角色信息
     *
     * @param param 使用{@link UserParam#id} 用户id {@link UserParam#roles}角色信息
     */
    @PostMapping(value = "/update/role")
    public void updateUserRole(@RequestBody UserParam param) {
        userService.updateUserRole(param.getId(), param.getRoles());
    }

    /**
     * 更新用户状态
     *
     * @param param 用户id {@link UserParam#id} 用户id{@link UserParam#state } 用户状态 0停用 1正常
     * @throws ServiceTipsException 业务异常
     */
    @PostMapping(value = "/update/state")
    public void updateState(@RequestBody UserParam param) throws ServiceTipsException {
        userService.updateState(param.getId(), EntityState.getByValue(param.getState()));
    }


}

@Data
class UserParam extends AbstractParam<User> {

    /**
     * 用户状态
     */
    private Integer state;
    /**
     * 角色信息
     */
    private List<Role> roles;
}