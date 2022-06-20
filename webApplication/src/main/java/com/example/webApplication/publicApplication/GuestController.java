package com.example.webApplication.publicApplication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.Thymeleaf;

@Controller
@RequestMapping(path = "/home")
public class GuestController {

    @GetMapping
    public String getAppInfo(Model model) {
        model.addAttribute("home", "Welcome to ParcForumé, the official F1 forum." +
                "\nIn this forum you can stay up to date with the most " +
                "\nnews in the racing world of Formula 1 " +
                "\n feel free to enter and read other users' posts " +
                "\nas well as reacting to them or commenting underneath!");
        return "homepage";
    }
}
