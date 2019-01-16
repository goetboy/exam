package pers.goetboy.controller;

import com.goetboy.core.exception.service.BaseServiceTipsMsgException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.goetboy.entity.sys.Menu;
import pers.goetboy.entity.sys.Role;
import pers.goetboy.services.MenuService;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController extends AbstractController {
    @Autowired
    MenuService menuService;

    /**
     * 获取用户列表
     */
    @GetMapping("/list")
    public List<Menu> list() {
        return menuService.listMenu();
    }

    /**
     * 获取用户信息
     *
     * @param menuId
     * @return
     */
    @GetMapping(value = "/get")
    public Menu get(Long menuId) {
        return menuService.get(menuId);
    }

    /**
     * 更新菜单信息
     * 可更新 nickName 字段
     * 可更新 remark字段
     *
     * @param menu 菜单信息
     */
    @PostMapping(value = "/update")
    public void update(@RequestBody MenuParam param) throws BaseServiceTipsMsgException {
        menuService.updateMenu(param.getMenu());
    }
    @PostMapping(value = "/save")
    public void save(@RequestBody MenuParam param) throws BaseServiceTipsMsgException {
        menuService.saveMenu(param.getMenu());
    }

    /**
     * 删除菜单信息
     *
     * @param menuId 菜单id
     */
    @PostMapping(value = "/delete")
    public void delete(@RequestBody MenuParam param) {
        menuService.deleteMenu(param.getMenuId());
    }

    /**
     * 更新菜单状态
     *
     * @param menuId 用户id
     * @param state  菜单状态 0停用 1正常
     * @throws BaseServiceTipsMsgException 异常信息
     */
    @PostMapping(value = "/update/state")
    public void updateUserState(@RequestBody MenuParam param) throws BaseServiceTipsMsgException {
        menuService.updateMenuState(param.getMenuId(), param.getState());
    }
}

@Data
class MenuParam {
    /**
     * 菜单id
     */
    private Long menuId;
    /**
     * 菜单状态
     */
    private Integer state;

    /**
     * 菜单信息
     */
    private Menu menu;
}