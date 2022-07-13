package com.example.webApplication.appuser;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class AppUserLoginDTO {
    @NonNull
    private String username;
    @NonNull
    private String password;
}
