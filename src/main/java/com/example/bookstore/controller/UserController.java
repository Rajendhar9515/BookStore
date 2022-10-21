package com.example.bookstore.controller;

import com.example.bookstore.dto.LoginDTO;
import com.example.bookstore.dto.ResponseDTO;
import com.example.bookstore.dto.UserDTO;
import com.example.bookstore.service.IUserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserInterface iUserInterface;


    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> save(@Valid @RequestBody UserDTO userDTO) {
        ResponseDTO responseDTO = new ResponseDTO("saved data successfully", iUserInterface.saveAll(userDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/userdata")
    public ResponseEntity<ResponseDTO> displayAll() {
        ResponseDTO responseDTO = new ResponseDTO("displayed all data successfully", iUserInterface.display());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/find/{token}")
    public ResponseEntity<ResponseDTO> findById(@PathVariable String token) {
        ResponseDTO responseDTO = new ResponseDTO("Get call for Id successfully", iUserInterface.findById(token));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/edit/{token}")
    public ResponseEntity<ResponseDTO> update(@Valid @RequestBody UserDTO userDTO, @PathVariable String token) {
        ResponseDTO responseDTO = new ResponseDTO("Updated data successfully", iUserInterface.update(userDTO, token));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{token}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable String token) {
        ResponseDTO responseDTO = new ResponseDTO("Deleted id successfully", iUserInterface.deleteById(token));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> userLogin(@RequestBody LoginDTO loginDTO) {
        ResponseDTO responseDTO = new ResponseDTO("User login successfully", iUserInterface.userLogin(loginDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
