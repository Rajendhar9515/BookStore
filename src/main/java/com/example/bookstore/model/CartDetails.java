package com.example.bookstore.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
public class CartDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.hibernate.annotations.ForeignKey(name = "none")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "userID")
    public UserDetails userDetails;

    @OneToOne
    @JoinColumn(name = "bookID")
    public BookDetails bookDetails;
    int quantity;
    double totalBookPrice;

    public CartDetails(UserDetails userDetails, BookDetails bookDetails, int quantity, double totalBookPrice) {
        this.userDetails = userDetails;
        this.bookDetails = bookDetails;
        this.quantity = quantity;
        this.totalBookPrice = totalBookPrice;
    }


}
