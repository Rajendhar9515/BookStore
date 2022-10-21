package com.example.bookstore.service;

import com.example.bookstore.dto.OrderDTO;

public interface IOrderService {
    Object placeOrder(String token, OrderDTO orderDTO);

    Object orderDetails(String token);

    Object cancelOrder(int id, String token);


}
