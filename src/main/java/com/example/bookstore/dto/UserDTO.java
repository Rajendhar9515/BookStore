package com.example.bookstore.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserDTO {

    @Pattern(regexp = "^[A-Za-z]{1,} [A-Za-z]{2,}$", message = "Name is Invalid")
    String fullName;

    @Pattern(regexp = "^(.+)@(\\S+)$", message = "Email is Invalid")
    String email;

    @Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]{7,29}$", message = "Please enter valid password")
    String password;

    @NotBlank(message = "Please enter address")
    String address;

    @Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]{7,29}$", message = "Please enter valid userName")
    String userName;

    @Pattern(regexp = "[0-9]{10}$", message = "Please enter valid phoneNumber")
    String phoneNumber;
}
