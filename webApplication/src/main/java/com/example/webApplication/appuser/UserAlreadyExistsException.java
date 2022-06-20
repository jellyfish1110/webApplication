package com.example.webApplication.appuser;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException() {
        super("This user is already registered in the system!");
    }
}
