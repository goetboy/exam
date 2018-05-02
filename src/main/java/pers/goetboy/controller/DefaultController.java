package pers.goetboy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {
   @GetMapping("/")
    String getHome(){
        return "index.html";
    }
    @GetMapping("/index")
    String getHome2(){
        return "index.html";
    }
    @GetMapping("/login")
    String getLogin(){
        return "login.html";
}

}
