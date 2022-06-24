package com.example.webApplication.appuser;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class AppUserDTO {
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
}
