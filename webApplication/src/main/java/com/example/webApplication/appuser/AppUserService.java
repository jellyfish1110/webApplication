package com.example.webApplication.appuser;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    //Method that returns all users via the findAll() JpaRepository method.
    public List<AppUser> getAllAppUsers() {
        return appUserRepository.findAll();
    }

    public Optional<AppUser> getAppUserById(Long id) throws UserNotFoundException {
        Optional<AppUser> optionalAppUser = appUserRepository.findById(id);
        if(optionalAppUser.isEmpty()) {
            throw new UserNotFoundException("User with such email does not exist!");
        }

        return optionalAppUser;
    }

    public Optional<AppUser> getAppUserByEmail(String email) throws UserNotFoundException {
        Optional<AppUser> appUserOptional = appUserRepository.findByEmail(email);
        if(appUserOptional.isEmpty()) {
            throw new UserNotFoundException("User with such email does not exist!");
        }

        return appUserOptional;
    }

    public void addNewAppUser(@RequestBody AppUser appUser) throws UserAlreadyExistsException {
        Optional<AppUser> userOptional = appUserRepository.findByEmail(appUser.getEmail());
        if(userOptional.isPresent()) {
            throw new UserAlreadyExistsException();
        }
        appUser.setUserRole(UserRole.USER);
        appUserRepository.save(appUser);
    }

    public void deleteUser(String email) throws UserNotFoundException {
        Optional<AppUser> appUserOptional = appUserRepository.findByEmail(email);
        if(appUserOptional.isEmpty()) {
            throw new UserNotFoundException("The user does not exist!");
        }
        AppUser appUser = appUserOptional.get();
        appUserRepository.delete(appUser);
    }
}
