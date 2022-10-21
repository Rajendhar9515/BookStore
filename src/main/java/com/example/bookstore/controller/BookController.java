package com.example.bookstore.controller;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.dto.ResponseDTO;
import com.example.bookstore.service.IBookInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    IBookInterface iBookInterface;

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> save(@RequestBody BookDTO bookDTO) {
        ResponseDTO responseDTO = new ResponseDTO("saved data successfully", iBookInterface.saveAll(bookDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @GetMapping("/bookdata")
    public ResponseEntity<ResponseDTO> displayAll() {
        ResponseDTO responseDTO = new ResponseDTO("Get call successfully", iBookInterface.display());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ResponseDTO> findById(@PathVariable int id) {
        ResponseDTO responseDTO = new ResponseDTO("Get call for Id successfully", iBookInterface.findById(id));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ResponseDTO> update(@Valid @RequestBody BookDTO bookDTO, @PathVariable int id) {
        ResponseDTO responseDTO = new ResponseDTO("Updated data successfully", iBookInterface.update(bookDTO, id));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable int id) {
        ResponseDTO responseDTO = new ResponseDTO("Deleted id successfully", iBookInterface.deleteById(id));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/sortByBookName")
    public ResponseEntity<ResponseDTO> sortByBookName() {
        ResponseDTO responseDTO = new ResponseDTO("Get call for BookNames data successfully", iBookInterface.sortByBookName());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/lowtohigh")
    public ResponseEntity<ResponseDTO> lowToHighPrice() {
        ResponseDTO responseDTO = new ResponseDTO("Get call for price successfully", iBookInterface.lowToHighPrice());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/hightolow")
    public ResponseEntity<ResponseDTO> highTOLowPrice() {
        ResponseDTO responseDTO = new ResponseDTO("Get call for price successfully", iBookInterface.highToLowPrice());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/updateQuantity/{id}/{quantity}")
    public ResponseEntity<ResponseDTO> updateBookQuantity(@PathVariable int id, @PathVariable int quantity) {
        ResponseDTO responseDTO = new ResponseDTO("Update quantity successfully", iBookInterface.updateBookQuantity(id, quantity));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}