package com.example.webApplication.publicApplication;

import com.example.webApplication.appuser.*;
import com.example.webApplication.userpost.UserPost;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private AppUserServiceInterface appUserInterface;

    private ModelMapper modelMapper;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping
    public String viewHomePage(Model model) throws UserNotFoundException {
        List<AppUser> userList = appUserInterface.getAllAppUsers();
        System.out.println(userList);
        //TODO: mapping all of the users as userDTOs
        model.addAttribute("userList", userList);
        return "homepage";
    }

    @GetMapping("/register")
    public String viewRegisterPage(Model model) {
        model.addAttribute("user", new AppUserRegistrationDTO());
        return "register";
    }

    @RequestMapping("/login")
    public String viewLoginPage(Model model) {
        model.addAttribute("user", new AppUserRegistrationDTO());
        return "login";
    }

    @PostMapping("/process_registration")
    public String processRegistration(AppUserRegistrationDTO appUserDTO) throws UserAlreadyExistsException {
//        System.out.println(appUserDTO.toString());

        appUserInterface.addNewAppUser(appUserDTO);
        return "register_success";
    }

    @GetMapping("/process_login")
    public String loginUser(AppUserLoginDTO appUserLoginDTO) throws UserNotFoundException, PasswordIncorrectException {
        String username = appUserLoginDTO.getUsername();
        String password = appUserLoginDTO.getPassword();

        AppUser appUser = appUserInterface.getAppUserByEmail(username).get();

        if(!BCrypt.checkpw(password, appUser.getPassword())) {
            throw new PasswordIncorrectException("User with such email-password combination not found");
        }

        return "login_successful";
    }

    @GetMapping("/forum")
    public String viewForum(Model model) {
        model.addAttribute("post", new UserPost());

        return "login_successful";
    }
}
