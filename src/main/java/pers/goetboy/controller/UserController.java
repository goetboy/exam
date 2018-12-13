package pers.goetboy.controller;

import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.goetboy.common.exception.service.ServiceTipsException;
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
     * @param id
     * @return
     */
    @GetMapping(value = "/get")
    public User get(Long id) {
        return userService.get(id);
    }

    /**
     * 更新用户信息
     * 可更新 nickName 字段
     * 可更新 remark字段
     *
     * @param user 用户信息
     */
    @PostMapping(value = "/update")
    public void update(User user) throws ServiceTipsException {
        userService.updateUser(user);
    }

    /**
     * 删除用户信息
     *
     * @param userId 用户id
     */
    @PostMapping(value = "/delete")
    public void delete(Long userId) {
        userService.deleteUser(userId);
    }

    /**
     * 更新用户角色信息
     *
     * @param userId 用户id
     * @param roles  角色信息
     */
    @PostMapping(value = "/update/role")
    public void updateUserRole(Long userId, List<Role> roles) {
        userService.updateUserRole(userId, roles);
    }

    /**
     * 更新用户状态
     *
     * @param userId 用户id
     * @param state  用户状态 0停用 1正常
     * @throws ServiceTipsException
     */
    @PostMapping(value = "/update/state")
    public void updateUserState(Long userId, Integer state) throws ServiceTipsException {
        userService.updateUserState(userId, state);
    }
}
