package com.example.webApplication.appuser;

import com.example.webApplication.exceptions.InvalidArgumentsException;
import com.example.webApplication.exceptions.UserAlreadyExistsException;
import com.example.webApplication.exceptions.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(path = "/application")
@RestController
public class AppUserController {

    @Autowired
    private ModelMapper modelMapper;

    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }



    /*
        get all users with their full information
     */
//    @GetMapping()
//    public List<AppUser> getAllAppUsers() {
//        return appUserService.getAllAppUsers();
//    }

    /*
        Method that returns all of the users as DTO objects
     */
    @GetMapping
    public List<AppUserDTO> getAllPosts() {
        return appUserService.getAllAppUsers().stream().map(appUser -> modelMapper.map(appUser, AppUserDTO.class))
                .collect(Collectors.toList());
    }



    /*
        get specific user by their id with all the information stored about them
     */
//    @GetMapping("/id{userId}")
//    public AppUser getAppUserById(@RequestParam Long userId) throws UserNotFoundException {
//        AppUser appUser  = appUserService.getAppUserById(userId).get();
//        return appUser;
//    }

    /*
        Get specific userDTO based on their id
     */
    @GetMapping("/id{userId}")
    public AppUserDTO getAppUserDTOById(@RequestParam Long userId) throws UserNotFoundException {
        AppUser appUser = appUserService.getAppUserById(userId).get();
        return modelMapper.map(appUser, AppUserDTO.class);
    }



    /*
        get specific user by their email with all of the information stored about them
     */
//    @GetMapping("/email{email}")
//    public AppUser getAppUserByEmail(@RequestParam String email) throws UserNotFoundException {
//        AppUser appUser = appUserService.getAppUserByEmail(email).get();
//        System.out.println("user is found");
//        return appUser;
//    }

    /*
        get a specific userDTO based on their email
     */
    @GetMapping("/email{email}")
    public AppUserDTO getAppUserDTOByEmail(@RequestParam String email) throws UserNotFoundException {
        AppUser appUser = appUserService.getAppUserByEmail(email).get();
        return modelMapper.map(appUser, AppUserDTO.class);
    }



    /*
        register user in the system
     */
//    @PostMapping("/user")
//    public void registerAppUser(@RequestBody AppUser appUser) throws UserAlreadyExistsException {
//        appUserService.addNewAppUser(appUser);
//    }

    /*
        register a user using DTO
     */
    @PostMapping("/register")
    public void registerAppUser(@RequestBody AppUserRegistrationDTO appUserRegistrationDTO) throws UserAlreadyExistsException {

        appUserService.addNewAppUser(appUserRegistrationDTO);
    }

    /*
        delete user from database
     */
    @DeleteMapping
    public void deleteAppUser(@RequestParam String email) throws UserNotFoundException {
        appUserService.deleteUser(email);
    }

    /*
        update user data
     */
    @PutMapping
    public void updateUser(@RequestParam String lookupEmail,
                           @RequestParam(required = false) String firstName,
                           @RequestParam(required = false) String lastName,
                           @RequestParam(required = false) String email,
                           @RequestParam(required = false) String password,
                           @RequestParam(required = false) String phoneNumber) throws UserNotFoundException, InvalidArgumentsException {

        if(firstName != null) {
            appUserService.updateFirstName(lookupEmail, firstName);
        }
        if(lastName != null) {
            appUserService.updateLastName(lookupEmail, lastName);
        }
        if(password != null) {
            appUserService.updatePassword(lookupEmail, password);
        }
        if(phoneNumber != null) {
            appUserService.updatePhoneNumber(lookupEmail, phoneNumber);
        }
        if(email != null) {
            appUserService.updateEmail(lookupEmail, email);
        }
        if(lookupEmail != null && firstName == null && lastName == null && email == null && password == null && phoneNumber == null) {
            throw new InvalidArgumentsException("Not null parameters required to update user");
        }
    }
}
