package com.example.webApplication.exceptions;

public class UserNotLoggedInException extends Exception {
    public UserNotLoggedInException(String s) {
        super(s);
    }
}
