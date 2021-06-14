package com.example.unsubscribe.dto;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Email;

@Data
public class AppUserDTO {
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private String surname;
    @NonNull
    @Email(message="Please enter valid email!")
    private String email;
    @NonNull
    private String password;
}
