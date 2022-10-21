package com.example.bookstore.controller;

import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.dto.ResponseDTO;
import com.example.bookstore.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ICartService iCartService;

    @PostMapping("/save/{token}")
    public ResponseEntity<ResponseDTO> save(@PathVariable String token, @RequestBody CartDTO cartDTO) {
        ResponseDTO responseDTO = new ResponseDTO("Saved data successfully", iCartService.saveAll(token, cartDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @GetMapping("/cartdata/{token}")
    public ResponseEntity<ResponseDTO> display(@PathVariable String token) {
        ResponseDTO responseDTO = new ResponseDTO("Get call data successfully", iCartService.displayAll(token));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("find/{id}/{token}")
    public ResponseEntity<ResponseDTO> findCartId(@PathVariable int id, @PathVariable String token) {
        ResponseDTO responseDTO = new ResponseDTO("Get call by Id successfully", iCartService.findById(id, token));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @PutMapping("/edit/{cartId}/{bookQuantity}")
    public ResponseEntity<ResponseDTO> update(@PathVariable int cartId, @PathVariable int bookQuantity, @RequestParam String token) {
        ResponseDTO responseDTO = new ResponseDTO("Update data successfully", iCartService.updateCartByQuantity(cartId, bookQuantity, token));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("remove/{id}/{token}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable int id, @PathVariable String token) {
        ResponseDTO responseDTO = new ResponseDTO("Deleted data successfully", iCartService.delete(id, token));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
