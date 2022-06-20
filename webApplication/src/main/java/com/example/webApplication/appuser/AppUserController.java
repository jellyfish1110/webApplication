package com.example.webApplication.appuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/application")
@RestController
public class AppUserController {

    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    //get all users
    @GetMapping()
    public List<AppUser> getAllAppUsers() {
        return appUserService.getAllAppUsers();
    }

    //get specific user by their id
    @GetMapping("/id{userId}")
    public AppUser getAppUserById(@RequestParam Long userId) throws UserNotFoundException {
        AppUser appUser  = appUserService.getAppUserById(userId).get();
        return appUser;
    }

    //get specific user by their email
    @GetMapping("/email{email}")
    public AppUser getAppUserByEmail(@RequestParam String email) throws UserNotFoundException {
        AppUser appUser = appUserService.getAppUserByEmail(email).get();
        System.out.println("user is found");
        return appUser;
    }

    //register user
    @PostMapping("/user")
    public void registerAppUser(@RequestBody AppUser appUser) throws UserAlreadyExistsException {
        appUserService.addNewAppUser(appUser);
    }

    //delete user
    @DeleteMapping
    public void deleteAppUser(@RequestParam String email) throws UserNotFoundException {
        appUserService.deleteUser(email);
    }

    //update user data
    @PutMapping
    public void updateUser(@RequestBody AppUser appUser) {

    }
}
