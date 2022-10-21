package com.example.bookstore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class OrderDetails {

    @Id
    @GeneratedValue()
    private Integer orderId;

    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @org.hibernate.annotations.ForeignKey(name = "none")
    public List<CartDetails> cartDetails;

    int userid;
    int totalBookQuantity;
    double totalBooksPrice;
    String address;

    boolean cancel;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime orderDate = LocalDateTime.now();

    public OrderDetails(List<CartDetails> cartData, String address, int userId, double totalBooksPrice, int quantity) {
        this.cartDetails = cartData;
        this.address = address;
        this.orderDate = LocalDateTime.now();
        this.userid = userId;
        this.totalBooksPrice = totalBooksPrice;
        this.totalBookQuantity = quantity;
    }

    public OrderDetails(String address, int userId, double totalPrice, int totalQuantity) {
        this.address = address;
        this.orderDate = LocalDateTime.now();
        this.userid = userId;
        this.totalBooksPrice = totalPrice;
        this.totalBookQuantity = totalQuantity;
    }
}
