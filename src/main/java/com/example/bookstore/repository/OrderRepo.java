package com.example.bookstore.repository;

import com.example.bookstore.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface OrderRepo extends JpaRepository<OrderDetails, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update bookstore.order_details set cancel = :cancel where order_id = :orderId", nativeQuery = true)
    void setCancel(boolean cancel, Integer orderId);

    @Query(value = "SELECT * FROM bookstore.order_details where userid = :userId", nativeQuery = true)
    List<OrderDetails> findOrderDetails(int userId);
}
