package com.example.bookstore.service;

import com.example.bookstore.dto.WishlistDTO;

public interface IWishlistInterface {
    Object addWishlist(WishlistDTO wishlistDTO, String token);

    Object display(String token);

    Object removeWishlist(int id, String token);
}
