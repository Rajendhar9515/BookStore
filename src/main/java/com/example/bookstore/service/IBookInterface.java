package com.example.bookstore.service;

import com.example.bookstore.dto.BookDTO;

public interface IBookInterface {
    Object saveAll(BookDTO bookDTO);

    Object display();

    Object findById(int id);

    Object update(BookDTO bookDTO, int id);

    Object deleteById(int id);

    Object sortByBookName();

    Object updateBookQuantity(int id, int quantity);

    Object lowToHighPrice();

    Object highToLowPrice();
}
