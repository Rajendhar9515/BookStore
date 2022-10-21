package com.example.bookstore.repository;

import com.example.bookstore.model.CartDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<CartDetails, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update bookstore.cart_details set quantity = :bookQuantity, total_Book_Price = :totalBookPrice where id = :cartId", nativeQuery = true)
    void updateCartByQuantity(int cartId, int bookQuantity, double totalBookPrice);

    @Query(value = "SELECT * FROM bookstore.cart_details  where userid = :user_id", nativeQuery = true)
    List<CartDetails> getCartDetails(int user_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM bookstore.cart_details WHERE userid=:user_id", nativeQuery = true)
    void deleteCartDetails(int user_id);

    @Query(value = "SELECT * FROM bookstore.cart_details where userid = :userId", nativeQuery = true)
    List<CartDetails> findCartDetails(int userId);

}
