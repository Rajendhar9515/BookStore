package com.example.bookstore.service;

import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.exceptionalHandler.BookStoreException;
import com.example.bookstore.model.BookDetails;
import com.example.bookstore.model.CartDetails;
import com.example.bookstore.model.UserDetails;
import com.example.bookstore.repository.BookRepo;
import com.example.bookstore.repository.CartRepo;
import com.example.bookstore.repository.UserRepo;
import com.example.bookstore.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {

    @Autowired
    CartRepo cartRepo;
    @Autowired
    BookRepo bookRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    TokenUtility tokenUtility;

    public CartDetails saveAll(String token, CartDTO cartDTO) {
        int userId = tokenUtility.decodeToken(token);
        Optional<UserDetails> userData = userRepo.findById(userId);
        Optional<BookDetails> bookData = bookRepo.findById(cartDTO.getBook_id());
        if (userData.isPresent() && bookData.isPresent() && bookData.get().getQuantity() >= cartDTO.getQuantity()) {
            List<CartDetails> cartData = cartRepo.findAll();
            for (CartDetails cartDetails : cartData) {
                if (cartDetails.getBookDetails().getId() == cartDTO.getBook_id() && cartDetails.getUserDetails().getId() == userId) {
                    throw new BookStoreException("This is duplicate value");
                }
            }
            double totalBookPrice = cartDTO.getQuantity() * bookData.get().getPrice();
            CartDetails cartDetails = new CartDetails(userData.get(), bookData.get(), cartDTO.getQuantity(), totalBookPrice);
            return cartRepo.save(cartDetails);
        } else {
            throw (new BookStoreException("provided details are not correct"));
        }

    }

    public List<CartDetails> displayAll(String token) {
        int userId = tokenUtility.decodeToken(token);
        List<CartDetails> cartData = cartRepo.findCartDetails(userId);
        if (userRepo.findById(userId).isPresent()) {
            if (cartData.isEmpty()) {
                throw new BookStoreException("This user cart details are not present");
            } else {
                return cartData;
            }
        } else {
            throw new BookStoreException("This userId is not present");
        }
    }

    public Optional<CartDetails> findById(int id, String token) {
        int userId = tokenUtility.decodeToken(token);
        int cartUserId = cartRepo.findById(id).get().getUserDetails().getId();
        if (cartRepo.findById(id).isPresent() && cartUserId != userId) {
            throw  new BookStoreException("Employee with id " + id + " does not match userid in cart");
        }else {
            return cartRepo.findById(id);
        }
    }

    public String updateCartByQuantity(int cartId, int bookQuantity, String token) {
        int userId = tokenUtility.decodeToken(token);
        int cartUserId = cartRepo.findById(cartId).get().getUserDetails().getId();
        if (cartRepo.findById(cartId).isPresent() && cartUserId == userId) {
            int bookId = cartRepo.findById(cartId).get().getBookDetails().getId();
            if (bookRepo.findById(bookId).get().getQuantity() >= bookQuantity) {
                double totalBookPrice = bookQuantity * bookRepo.findById(bookId).get().getPrice();
                cartRepo.updateCartByQuantity(cartId, bookQuantity, totalBookPrice);
                return "Updated data by quantity successfully ";
            } else {
                throw new BookStoreException("book Quantity is more then available quantity");
            }
        } else {
            throw new BookStoreException("This id is not present");
        }
    }

    public String delete(int id, String token) {
        int userId = tokenUtility.decodeToken(token);
        Optional<CartDetails> cartId = cartRepo.findById(id);
        if (cartId.isPresent() && userId == cartId.get().getUserDetails().getId()) {
            cartRepo.deleteById(id);
            return "data deleted successfully";
        } else {
            throw new BookStoreException("This id not present");
        }

    }

}
