package pers.goetboy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.goetboy.common.AbstractController;
import pers.goetboy.common.AbstractParam;
import pers.goetboy.common.exception.service.ServiceTipsException;
import pers.goetboy.entity.AbstractEntity;
import pers.goetboy.entity.EntityState;
import pers.goetboy.entity.sys.Menu;
import pers.goetboy.services.MenuService;


/**
 * @author goetb
 */
@RestController
@RequestMapping("/menu")
public class MenuController extends AbstractController {
    private final
    MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 获取用户列表
     */
    @GetMapping("/list")
    public IPage<Menu> list(Integer current, Integer size) {
        return menuService.page(new Page(current,size));
    }

    /**
     * 获取菜单信息
     *
     * @param menuId
     * 菜单id
     * @return 菜单信息
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
     * @param param 菜单信息
     */
    @PostMapping(value = "/update")
    public void update(@RequestBody MenuParam param) throws ServiceTipsException {
        menuService.update(param.getEntity());
    }
    @PostMapping(value = "/save")
    public void save(@RequestBody MenuParam param) throws ServiceTipsException {
        menuService.save(param.getEntity());
    }

    /**
     * 删除菜单信息
     *
     * @param param menuId 菜单id
     */
    @PostMapping(value = "/delete")
    public void delete(@RequestBody MenuParam param) {
        menuService.delete(param.getId());
    }

    /**
     * 更新菜单状态
     *
     * @param param menuId 用户id | state  菜单状态 0停用 1正常
     * @throws ServiceTipsException 异常信息
     */
    @PostMapping(value = "/update/state")
    public void updateUserState(@RequestBody MenuParam param) throws ServiceTipsException {
        menuService.updateState(param.getId(), EntityState.getByValue(param.getState()));
    }
}

@Data
class MenuParam extends AbstractParam<Menu> {

}