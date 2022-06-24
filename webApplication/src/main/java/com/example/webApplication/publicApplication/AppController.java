package com.example.webApplication.publicApplication;

import com.example.webApplication.appuser.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.Thymeleaf;

@Controller
public class AppController {

//    @GetMapping
//    public String getAppInfo(Model model) {
//        model.addAttribute("/users", "Welcome to ParcForumé, the official F1 forum." +
//                "\nIn this forum you can stay up to date with the most " +
//                "\nnews in the racing world of Formula 1 " +
//                "\n feel free to enter and read other users' posts " +
//                "\nas well as reacting to them or commenting underneath!");
//        return "index";
//    }

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }
}
