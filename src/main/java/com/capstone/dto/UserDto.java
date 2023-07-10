package com.capstone.dto;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;


    private String firstName;

    private String lastName;

    @Email
    private String email;


    private String password;
    private String matchPassword;
}
