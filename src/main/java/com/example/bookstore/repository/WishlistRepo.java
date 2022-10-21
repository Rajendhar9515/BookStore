package com.example.bookstore.repository;

import com.example.bookstore.model.WishlistDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepo extends JpaRepository<WishlistDetails, Integer> {

    @Query(value = "SELECT * FROM bookstore.wishlist_details where user_id = :userId", nativeQuery = true)
    List<WishlistDetails> getUserWishlist(int userId);
}
