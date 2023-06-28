package spring.boot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import spring.boot_security.service.UserServiceImp;

import java.security.Principal;

@Controller
public class UserController {

    private final UserServiceImp service;

    @Autowired
    public UserController(UserServiceImp service) {
        this.service = service;
    }

    @GetMapping(value = "/user")
    public String userPage(Model model, Principal principal) {
        model.addAttribute("user", service.getUserByName(principal.getName()));
        return "/user";
    }
}
