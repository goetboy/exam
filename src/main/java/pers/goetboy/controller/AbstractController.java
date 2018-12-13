package pers.goetboy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import pers.goetboy.entity.sys.User;

public class AbstractController {

    protected User getUser() {
        final User user = new User();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        return user;
    }

    protected ModelAndView result(HttpStatus status) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setStatus(status);
        return modelAndView;
    }

    protected ModelAndView result(HttpStatus status, Object object) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setStatus(status);
        modelAndView.addObject(object);
        
        return modelAndView;
    }

    protected ModelAndView result(HttpStatus status, View view) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setStatus(status);
        modelAndView.setView(view);

        return modelAndView;
    }

    protected ModelAndView result(HttpStatus status, View view, Object object) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setStatus(status);
        modelAndView.setView(view);
        modelAndView.addObject(object);

        return modelAndView;
    }

    protected ModelAndView result(HttpStatus status, String viewName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setStatus(status);
        modelAndView.setViewName(viewName);
        return modelAndView;
    }

    protected ModelAndView result(HttpStatus status, String viewName, Object object) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setStatus(status);
        modelAndView.setViewName(viewName);
        modelAndView.addObject(object);

        return modelAndView;
    }
}
