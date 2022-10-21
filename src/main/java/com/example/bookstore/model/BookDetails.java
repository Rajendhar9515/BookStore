package com.example.bookstore.model;

import com.example.bookstore.dto.BookDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class BookDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookID", nullable = false)
    private Integer id;

    String bookName;
    double price;
    String authorName;
    int quantity;
    String profilePic;

    public BookDetails(BookDTO bookDTO) {
        this.bookName = bookDTO.getBookName();
        this.quantity = bookDTO.getQuantity();
        this.authorName = bookDTO.getAuthorName();
        this.price = bookDTO.getPrice();
        this.profilePic = bookDTO.getProfilePic();
        this.id = id;
    }

    public BookDetails(BookDTO bookDTO, int id) {
        this.bookName = bookDTO.getBookName();
        this.quantity = bookDTO.getQuantity();
        this.authorName = bookDTO.getAuthorName();
        this.price = bookDTO.getPrice();
        this.profilePic = bookDTO.getProfilePic();
        this.id = id;
    }

}
