package com.example.webApplication.appuser;

import com.example.webApplication.exceptions.InvalidArgumentsException;
import com.example.webApplication.exceptions.UserAlreadyExistsException;
import com.example.webApplication.exceptions.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface AppUserServiceInterface {

    List<AppUser> getAllAppUsers();

    Optional<AppUser> getAppUserById(Long id) throws UserNotFoundException;

    Optional<AppUser> getAppUserByEmail(String email) throws UserNotFoundException;

    void addNewAppUser(AppUserRegistrationDTO appUserDTO) throws UserAlreadyExistsException;

    void deleteUser(String email) throws UserNotFoundException;

    void updateFirstName(String lookupEmail, String firstName) throws UserNotFoundException, InvalidArgumentsException;

    void updateLastName(String lookupEmail, String lastName) throws UserNotFoundException, InvalidArgumentsException;

    void updateEmail(String lookupEmail, String email) throws UserNotFoundException, InvalidArgumentsException;

    void updatePassword(String lookupEmail, String password) throws UserNotFoundException, InvalidArgumentsException;

    void updatePhoneNumber(String lookupEmail, String phoneNumber) throws UserNotFoundException, InvalidArgumentsException;
}
