package pers.goetboy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import pers.goetboy.entity.sys.User;
import pers.goetboy.services.UserService;

@RestController()
@RequestMapping("/user")
public class UserController extends AbstractController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public User get(Long id) {
        return userService.get(id);

    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)

    public void save(User user) {
        userService.save(user);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(User user) {

        userService.update(user);

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete(Long id) {
        userService.delete(id);
    }
}
