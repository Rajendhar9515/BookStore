package com.example.bookstore.repository;

import com.example.bookstore.model.BookDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface BookRepo extends JpaRepository<BookDetails, Integer> {

    @Query(value = "SELECT * FROM bookstore.book_details order by book_name", nativeQuery = true)
    List<BookDetails> sortByBookName();

    @Modifying
    @Transactional
    @Query(value = "update bookstore.book_details set quantity = :quantity where bookid = :id", nativeQuery = true)
    void updateBookQuantity(int id, int quantity);

    @Query(value = "SELECT * FROM bookstore.book_details order by price", nativeQuery = true)
    List<BookDetails> lowToHighPrice();

    @Query(value = "SELECT * FROM bookstore.book_details order by price DESC", nativeQuery = true)
    List<BookDetails> highToLowPrice();

}