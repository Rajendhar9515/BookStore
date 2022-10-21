package com.example.bookstore.controller;

import com.example.bookstore.dto.OrderDTO;
import com.example.bookstore.dto.ResponseDTO;
import com.example.bookstore.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    IOrderService iOrderService;

    @PostMapping("/save/{token}")
    public ResponseEntity<ResponseDTO> save(@PathVariable String token, @RequestBody OrderDTO orderDTO) {
        ResponseDTO responseDTO = new ResponseDTO("saved data successfully", iOrderService.placeOrder(token, orderDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/orderData/{token}")
    public ResponseEntity<ResponseDTO> display(@PathVariable String token) {
        ResponseDTO responseDTO = new ResponseDTO("Get call successfully", iOrderService.orderDetails(token));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @PutMapping("/cancel/{id}/{token}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable int id, @PathVariable String token) {
        ResponseDTO responseDTO = new ResponseDTO("Order cancelled", iOrderService.cancelOrder(id, token));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
