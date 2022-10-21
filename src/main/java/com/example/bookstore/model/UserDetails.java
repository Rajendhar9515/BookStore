package com.example.bookstore.model;

import com.example.bookstore.dto.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID", nullable = false)
    private Integer id;

    String fullName;
    String email;
    String password;
    String address;
    String userName;
    String phoneNumber;
    String token;

    public UserDetails(UserDTO userDTO) {
        this.fullName = userDTO.getFullName();
        this.email = userDTO.getEmail();
        this.password = userDTO.getPassword();
        this.address = userDTO.getAddress();
        this.userName = userDTO.getUserName();
        this.phoneNumber = userDTO.getPhoneNumber();
        this.token = token;
        this.id = id;
    }

    public UserDetails(UserDTO userDTO, int id, String token) {
        this.fullName = userDTO.getFullName();
        this.email = userDTO.getEmail();
        this.password = userDTO.getPassword();
        this.address = userDTO.getAddress();
        this.userName = userDTO.getUserName();
        this.phoneNumber = userDTO.getPhoneNumber();
        this.token = token;
        this.id = id;
    }


}
