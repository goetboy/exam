package pers.goetboy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pers.goetboy.entity.sys.Menu;
import pers.goetboy.services.MenuService;

import java.util.List;
@RestController
@RequestMapping("/menu")
public class MenuController extends AbstractController {
    @Autowired
    MenuService menuService;

    /**
     * 获取当前登陆用户菜单
     *
     * @return
     */

   @RequestMapping(value = "/list", method = RequestMethod.GET)
    //@GetMapping("/list")
    public List<Menu> listByUser() {

        return menuService.findByUserName(getUser().getUsername());
    }

   @RequestMapping(value = "/get", method = RequestMethod.GET)
    //@GetMapping("/{id}")
    public Menu get(Long id) {
        return menuService.get(id);

    }
   // @PutMapping("")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(Menu menu) {
        menuService.save(menu);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(Menu menu) {

        menuService.update(menu);

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete(Long id) {
        menuService.delete(id);
    }

}
