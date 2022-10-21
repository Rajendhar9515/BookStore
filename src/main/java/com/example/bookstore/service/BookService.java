package com.example.bookstore.service;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.exceptionalHandler.BookStoreException;
import com.example.bookstore.model.BookDetails;
import com.example.bookstore.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookInterface {
    @Autowired
    BookRepo bookRepo;

    public BookDetails saveAll(BookDTO bookDTO) {
        List<BookDetails> bookDetails = bookRepo.findAll();
        for (BookDetails bookData : bookDetails) {
            if (bookData.getBookName().equals(bookDTO.getBookName())) {
                throw new BookStoreException("This is duplicate value");
            }
        }
        BookDetails bookDetails1 = new BookDetails(bookDTO);
        return bookRepo.save(bookDetails1);
    }

    public List<BookDetails> display() {
        return bookRepo.findAll();
    }

    public BookDetails findById(int id) {
        return bookRepo.findById(id).orElseThrow(() ->
                new BookStoreException("Employee with id " + id + " does not exist in database"));
    }

    public BookDetails update(BookDTO bookDTO, int id) {
        if (bookRepo.findById(id).isPresent()) {
            List<BookDetails> bookDetails = bookRepo.findAll();
            for (BookDetails bookData : bookDetails) {
                if (bookData.getId() != id && bookData.getBookName().equals(bookDTO.getBookName())) {
                    throw new BookStoreException("This is duplicate value");
                }
            }
            BookDetails bookDetails1 = new BookDetails(bookDTO, id);
            return bookRepo.save(bookDetails1);
        } else {
            throw (new BookStoreException("This id is not present"));
        }
    }

    public String deleteById(int id) {
        Optional<BookDetails> bookId = bookRepo.findById(id);
        if (bookId.isPresent()) {
            bookRepo.deleteById(id);
            return "data deleted by id successfully";
        } else {
            throw (new BookStoreException("This id is not present"));
        }
    }

    public List<BookDetails> sortByBookName() {
        if (bookRepo.sortByBookName().isEmpty()) {
            throw (new BookStoreException("Books are not there"));
        } else {
            return bookRepo.sortByBookName();
        }
    }

    public List<BookDetails> lowToHighPrice() {
        if (bookRepo.lowToHighPrice().isEmpty()) {
            throw (new BookStoreException("Price is Not there"));
        } else {
            return bookRepo.lowToHighPrice();
        }
    }

    public List<BookDetails> highToLowPrice() {
        if (bookRepo.highToLowPrice().isEmpty()) {
            throw (new BookStoreException("Price is Not there"));
        } else {
            return bookRepo.highToLowPrice();
        }
    }

    public String updateBookQuantity(int id, int quantity) {
        if (bookRepo.findById(id).isPresent()) {
            bookRepo.updateBookQuantity(id, quantity);
            System.out.println(bookRepo.findAll());
            return "updated quantity by id successfully";
        } else {
            throw (new BookStoreException("This id is not present"));
        }

    }
}
