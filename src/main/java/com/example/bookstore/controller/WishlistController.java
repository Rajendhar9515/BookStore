package com.example.bookstore.controller;

import com.example.bookstore.dto.ResponseDTO;
import com.example.bookstore.dto.WishlistDTO;
import com.example.bookstore.service.IWishlistInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    IWishlistInterface iWishlistInterface;

    @RequestMapping("/save/{token}")
    public ResponseEntity<ResponseDTO> addWishlist(@RequestBody WishlistDTO wishlistDTO, @PathVariable String token) {
        ResponseDTO responseDTO = new ResponseDTO("add wishlist", iWishlistInterface.addWishlist(wishlistDTO, token));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getAll/{token}")
    public ResponseEntity<ResponseDTO> getAll(@PathVariable String token) {
        ResponseDTO responseDTO = new ResponseDTO("Display all wishlist", iWishlistInterface.display(token));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


    @DeleteMapping("/remove/{id}/{token}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable int id, @PathVariable String token) {
        ResponseDTO responseDTO = new ResponseDTO("Deleted data successfully", iWishlistInterface.removeWishlist(id, token));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
