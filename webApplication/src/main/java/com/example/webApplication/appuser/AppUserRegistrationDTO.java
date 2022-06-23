package com.example.webApplication.appuser;

import lombok.Data;
import lombok.NonNull;

@Data
public class AppUserRegistrationDTO {
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String email;
    @NonNull
    private String username;
    @NonNull
    private String password;
}
