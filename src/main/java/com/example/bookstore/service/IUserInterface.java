package com.example.bookstore.service;

import com.example.bookstore.dto.LoginDTO;
import com.example.bookstore.dto.UserDTO;

public interface IUserInterface {
    Object saveAll(UserDTO userDTO);

    Object display();

    Object findById(String token);

    Object update(UserDTO userDTO, String token);

    Object deleteById(String token);

    Object userLogin(LoginDTO loginDTO);
}
