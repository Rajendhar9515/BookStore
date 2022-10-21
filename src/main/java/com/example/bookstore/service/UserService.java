package com.example.bookstore.service;

import com.example.bookstore.dto.LoginDTO;
import com.example.bookstore.dto.UserDTO;
import com.example.bookstore.exceptionalHandler.BookStoreException;
import com.example.bookstore.model.UserDetails;
import com.example.bookstore.repository.UserRepo;
import com.example.bookstore.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserInterface {

    @Autowired
    UserRepo userRepo;

    @Autowired
    TokenUtility tokenUtility;

    public UserDetails saveAll(UserDTO userDTO) {
        List<UserDetails> userDetails = userRepo.findAll();
        for (UserDetails userData : userDetails) {
            if (userData.getUserName().equals(userDTO.getUserName()) || userData.getEmail().equals(userDTO.getEmail())) {
                throw new BookStoreException("This is duplicate value");
            }
        }
        UserDetails userDetails1 = new UserDetails(userDTO);
        userRepo.save(userDetails1);
        String token = tokenUtility.createToken(userDetails1.getId());
        userDetails1.setToken(token);
        return userRepo.save(userDetails1);
    }

    public List<UserDetails> display() {
        return userRepo.findAll();
    }

    public UserDetails findById(String token) {
        int id = tokenUtility.decodeToken(token);
        return userRepo.findById(id).orElseThrow(() ->
                new BookStoreException("Employee with id " + id + " does not exist in database"));
    }

    public UserDetails update(UserDTO userDTO, String token) {
        int id = tokenUtility.decodeToken(token);
        if (userRepo.findById(id).isPresent()) {
            List<UserDetails> userDetails = userRepo.findAll();
            for (UserDetails userData : userDetails) {
                if (userData.getId() != id && userData.getUserName().equals(userDTO.getUserName())) {
                    throw new BookStoreException("this is duplicate value");
                }
            }
            UserDetails userDetails1 = new UserDetails(userDTO, id, token);
            return userRepo.save(userDetails1);
        } else {
            throw (new BookStoreException("This id is not present"));
        }
    }

    public String deleteById(String token) {
        int id = tokenUtility.decodeToken(token);
        Optional<UserDetails> userId = userRepo.findById(id);
        if (userId.isPresent()) {
            userRepo.deleteById(id);
            return "data deleted by id successfully";
        } else {
            throw (new BookStoreException("This id is not present"));
        }
    }

    public String userLogin(LoginDTO loginDTO) {
        List<UserDetails> userDetails = userRepo.findAll();
        for (UserDetails user : userDetails) {
            if (user.getEmail().equals(loginDTO.getEmail()) && user.getPassword().equals(loginDTO.getPassword())) {
                return "Login successfully";
            }
        }
        throw new BookStoreException("This record not found");
    }

}
