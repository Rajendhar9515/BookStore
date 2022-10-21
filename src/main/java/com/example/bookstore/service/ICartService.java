package com.example.bookstore.service;

import com.example.bookstore.dto.CartDTO;

public interface ICartService {
    Object saveAll(String token, CartDTO cartDTO);

    Object displayAll(String token);

    Object findById(int id, String token);

    Object updateCartByQuantity(int id, int bookQuantity, String token);

    Object delete(int id, String token);


}
