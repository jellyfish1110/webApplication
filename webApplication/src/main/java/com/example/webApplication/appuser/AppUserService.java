package com.example.webApplication.appuser;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {

    private final ModelMapper modelMapper;
    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserService(ModelMapper modelMapper, AppUserRepository appUserRepository) {
        this.modelMapper = modelMapper;
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

    /*
        Register user into the database.
     */
    public void addNewAppUser(AppUserRegistrationDTO appUserDTO) throws UserAlreadyExistsException {

        Optional<AppUser> userOptional = appUserRepository.findByEmail(appUserDTO.getEmail());
        if(userOptional.isPresent()) {
            throw new UserAlreadyExistsException();
        }
        AppUser userToRegister = modelMapper.map(appUserDTO, AppUser.class);
        userToRegister.setUserRole(UserRole.USER);

        appUserRepository.save(userToRegister);
    }

    public void deleteUser(String email) throws UserNotFoundException {
        Optional<AppUser> appUserOptional = appUserRepository.findByEmail(email);
        if(appUserOptional.isEmpty()) {
            throw new UserNotFoundException("This user does not exist1");
        }
        AppUser appUser = appUserOptional.get();
        appUserRepository.delete(appUser);
    }

    public void updateFirstName(String lookupEmail, String firstName) throws UserNotFoundException, InvalidArgumentsException {
        Optional<AppUser> appUserOptional = appUserRepository.findByEmail(lookupEmail);
        if(appUserOptional.isEmpty()) {
            throw new UserNotFoundException("This user does not exist!");
        }
        AppUser appUserToUpdate = appUserOptional.get();
        if(appUserToUpdate.getFirstName().equals(firstName)) {
            throw new InvalidArgumentsException("New first name must not match your old name");
        }
        appUserToUpdate.setFirstName(firstName);
        appUserRepository.save(appUserToUpdate);
    }

    public void updateLastName(String lookupEmail, String lastName) throws UserNotFoundException, InvalidArgumentsException {
        Optional<AppUser> appUserOptional = appUserRepository.findByEmail(lookupEmail);
        if(appUserOptional.isEmpty()) {
            throw new UserNotFoundException("This user does not exist!");
        }
        AppUser appUserToUpdate = appUserOptional.get();
        if(appUserToUpdate.getLastName().equals(lastName)) {
            throw new InvalidArgumentsException("New last name must not match your old name!");
        }
        appUserToUpdate.setLastName(lastName);
        appUserRepository.save(appUserToUpdate);
    }

    public void updateEmail(String lookupEmail, String email) throws UserNotFoundException, InvalidArgumentsException {
        Optional<AppUser> appUserOptional = appUserRepository.findByEmail(lookupEmail);
        if(appUserOptional.isEmpty()) {
            throw new UserNotFoundException("This user does not exist!");
        }
        AppUser appUserToUpdate = appUserOptional.get();
        if(lookupEmail.equals(email)) {
            throw new InvalidArgumentsException("New email must not match your old email!");
        }
        appUserToUpdate.setEmail(email);
        appUserRepository.save(appUserToUpdate);
    }

    public void updatePassword(String lookupEmail, String password) throws UserNotFoundException, InvalidArgumentsException {
        Optional<AppUser> appUserOptional = appUserRepository.findByEmail(lookupEmail);
        if(appUserOptional.isEmpty()) {
            throw new UserNotFoundException("This user does not exist!");
        }
        AppUser appUserToUpdate = appUserOptional.get();
        if(appUserToUpdate.getPassword().equals(password)) {
            throw new InvalidArgumentsException("New password must not match your old one!");
        }
        appUserToUpdate.setPassword(password);
        appUserRepository.save(appUserToUpdate);
    }

    public void updatePhoneNumber(String lookupEmail, String phoneNumber) throws UserNotFoundException, InvalidArgumentsException {
        Optional<AppUser> appUserOptional = appUserRepository.findByEmail(lookupEmail);
        if(appUserOptional.isEmpty()) {
            throw new UserNotFoundException("This user does not exist!");
        }
        AppUser appUserToUpdate = appUserOptional.get();
        if(appUserToUpdate.getPhoneNumber().equals(phoneNumber)) {
            throw new InvalidArgumentsException("New phone number must not match your old one!");
        }
        appUserToUpdate.setPhoneNumber(phoneNumber);
        appUserRepository.save(appUserToUpdate);
    }
}
