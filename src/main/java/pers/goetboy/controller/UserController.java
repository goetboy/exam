package pers.goetboy.controller;

import com.goetboy.core.exception.service.BaseServiceTipsMsgException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.goetboy.entity.sys.Role;
import pers.goetboy.entity.sys.User;
import pers.goetboy.services.UserService;

import java.util.List;

@RestController()
@RequestMapping("/user")
public class UserController extends AbstractController {
    @Autowired
    UserService userService;

    /**
     * 获取用户列表
     */
    @GetMapping("/list")
    public List<User> list() {
        return userService.listUser();
    }

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
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
     * @param param 使用 {@link Param#user}用户信息
     */
    @PostMapping(value = "/update")
    public void update(@RequestBody Param param) throws BaseServiceTipsMsgException {
        userService.updateUser(param.getUser());
    }

    /**
     * 删除用户信息
     *
     * @param param 使用 {@link Param#userId} 用户id
     */
    @PostMapping(value = "/delete")
    public void delete(@RequestBody Param param) {
        userService.deleteUser(param.getUserId());
    }

    /**
     * 更新用户角色信息
     *
     * @param param 使用{@link Param#userId} 用户id {@link Param#roles}角色信息
     */
    @PostMapping(value = "/update/role")
    public void updateUserRole(@RequestBody Param param) {
        userService.updateUserRole(param.getUserId(), param.getRoles());
    }

    /**
     * 更新用户状态
     *
     * @param param 用户id {@link Param#userId} 用户id{@link Param#state } 用户状态 0停用 1正常
     * @throws BaseServiceTipsMsgException
     */
    @PostMapping(value = "/update/state")
    public void updateUserState(@RequestBody Param param) throws BaseServiceTipsMsgException {
        userService.updateUserState(param.getUserId(), param.getState());
    }


}

@Data
class Param {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户状态
     */
    private Integer state;
    /**
     * 角色信息
     */
    private List<Role> roles;
    /**
     * 用户信息
     */
    private User user;


}