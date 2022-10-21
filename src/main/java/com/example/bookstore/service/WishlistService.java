package com.example.bookstore.service;

import com.example.bookstore.dto.WishlistDTO;
import com.example.bookstore.exceptionalHandler.BookStoreException;
import com.example.bookstore.model.BookDetails;
import com.example.bookstore.model.UserDetails;
import com.example.bookstore.model.WishlistDetails;
import com.example.bookstore.repository.BookRepo;
import com.example.bookstore.repository.UserRepo;
import com.example.bookstore.repository.WishlistRepo;
import com.example.bookstore.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistService implements IWishlistInterface {

    @Autowired
    WishlistRepo wishlistRepo;
    @Autowired
    BookRepo bookRepo;
    @Autowired
    TokenUtility tokenUtility;
    @Autowired
    UserRepo userRepo;

    @Override
    public WishlistDetails addWishlist(WishlistDTO wishlistDTO, String token) {
        int userId = tokenUtility.decodeToken(token);
        List<WishlistDetails> wishlistDetails = wishlistRepo.findAll();
        Optional<BookDetails> bookData = bookRepo.findById(wishlistDTO.getBookId());
        Optional<UserDetails> userData = userRepo.findById(userId);
        if (bookData.isPresent() && userData.isPresent()) {
            for (WishlistDetails wishlistDetail : wishlistDetails) {
                if (wishlistDetail.getUserDetails().getId() == userId && wishlistDetail.getBookDetails().getId() == wishlistDTO.getBookId()) {
                    throw new BookStoreException("This book already added in the wishlist");
                }
            }
            WishlistDetails wishlistData = new WishlistDetails(bookData.get(), userData.get());
            return wishlistRepo.save(wishlistData);
        } else {
            throw new BookStoreException("book id is not present");
        }
    }


    @Override
    public List<WishlistDetails> display(String token) {
        int userId = tokenUtility.decodeToken(token);
        List<WishlistDetails> wishlistData = wishlistRepo.getUserWishlist(userId);
        if (userRepo.findById(userId).isPresent()) {
            if (wishlistData.isEmpty()) {
                throw new BookStoreException("This user wishlist details are not present");
            } else {
                return wishlistData;
            }
        } else {
            throw new BookStoreException("This id is not present");
        }
    }

    @Override
    public String removeWishlist(int id, String token) {
        int userId = tokenUtility.decodeToken(token);
        Optional<WishlistDetails> wishlistData = wishlistRepo.findById(id);
        if (wishlistData.isPresent() && userRepo.findById(userId).isPresent()) {
            wishlistRepo.deleteById(id);
            return "Removed book from wishlist successfully";
        } else {
            throw new BookStoreException("This id is not present");
        }
    }


}
