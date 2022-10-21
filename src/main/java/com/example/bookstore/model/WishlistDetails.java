package com.example.bookstore.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
public class WishlistDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "bookId")
    public BookDetails bookDetails;

    @OneToOne
    @JoinColumn(name = "userId")
    public UserDetails userDetails;

    public WishlistDetails(BookDetails bookData, UserDetails userData) {
        this.id = id;
        this.bookDetails = bookData;
        this.userDetails = userData;
    }
}
