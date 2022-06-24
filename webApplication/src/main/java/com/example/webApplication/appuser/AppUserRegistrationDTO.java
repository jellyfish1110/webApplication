package com.example.webApplication.appuser;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;

@Data
public class AppUserRegistrationDTO {
    @NotEmpty
    @NonNull
    private String firstName;
    @NotEmpty
    @NonNull
    private String lastName;
    @NotEmpty
    @NonNull
    private String email;
    @NotEmpty
    @NonNull
    private String username;
    @NotEmpty
    @NonNull
    private String password;
}
