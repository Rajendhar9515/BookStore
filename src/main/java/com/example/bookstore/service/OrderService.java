package com.example.bookstore.service;

import com.example.bookstore.dto.OrderDTO;
import com.example.bookstore.email.EmailService;
import com.example.bookstore.exceptionalHandler.BookStoreException;
import com.example.bookstore.model.BookDetails;
import com.example.bookstore.model.CartDetails;
import com.example.bookstore.model.OrderDetails;
import com.example.bookstore.model.UserDetails;
import com.example.bookstore.repository.BookRepo;
import com.example.bookstore.repository.CartRepo;
import com.example.bookstore.repository.OrderRepo;
import com.example.bookstore.repository.UserRepo;
import com.example.bookstore.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    @Autowired
    OrderRepo orderRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    CartRepo cartRepo;
    @Autowired
    BookRepo bookRepo;

    @Autowired
    TokenUtility tokenUtility;

    @Autowired
    EmailService emailService;


    List<CartDetails> cartList = new ArrayList<>();

    public OrderDetails placeOrder(String token, OrderDTO orderDTO) {
        int userId = tokenUtility.decodeToken(token);
        List<CartDetails> cartData = cartRepo.getCartDetails(userId);
        List<UserDetails> userData = userRepo.getUserId(userId);

        if (cartData.isEmpty() || userData.isEmpty()) {
            throw new BookStoreException("This userId is not present");
        } else {
            int totalQuantity = 0;
            double totalPrice = 0;
            for (CartDetails cartData1 : cartData) {
                totalPrice = totalPrice + cartData1.getTotalBookPrice();
                totalQuantity = totalQuantity + cartData1.getQuantity();
            }
            String address = userRepo.findById(userId).get().getAddress();
            String userAddress = orderDTO.getAddress().isEmpty() ? address : orderDTO.getAddress();
            OrderDetails orderDetails = new OrderDetails(cartData, userAddress, userId, totalPrice, totalQuantity);
            UserDetails userDetails = userRepo.findById(userId).get();
            OrderDetails orderDetails1 = new OrderDetails(userAddress, userId, totalPrice, totalQuantity);
            emailService.sendEmail(userDetails.getEmail(), "your order placed successfully", "your ordered books successfully \n" + orderDetails1 );
            orderRepo.save(orderDetails);
            List<CartDetails> cartDetails1 = orderDetails.getCartDetails();
            for (CartDetails cartDetail : cartDetails1) {
                int quantity = cartDetail.getBookDetails().getQuantity() - cartDetail.getQuantity();
                int bookId = cartDetail.getBookDetails().getId();
                bookRepo.updateBookQuantity(bookId, quantity);
                cartList.add(cartDetail);
            }
            cartRepo.deleteCartDetails(userId);
            return orderDetails;
        }
    }


    public List<OrderDetails> orderDetails(String token) {
        int userId = tokenUtility.decodeToken(token);
        List<OrderDetails> orderData = orderRepo.findOrderDetails(userId);
        if (userRepo.findById(userId).isPresent()) {
            if (orderData.isEmpty()) {
                throw new BookStoreException("This user order details are not present");
            } else {
                return orderData;
            }
        } else {
            throw new BookStoreException("This userId is not present");
        }

    }

    public String cancelOrder(int id, String token) {
        int userId = tokenUtility.decodeToken(token);
        Optional<OrderDetails> orderId = orderRepo.findById(id);
        if (orderId.isPresent() && orderId.get().getUserid() == userId && orderId.get().isCancel() != true) {
            orderRepo.setCancel(true, id);
            List<CartDetails> cartDetails = cartList;
            for (CartDetails cartDetail : cartDetails) {
                int quantity = cartDetail.getBookDetails().getQuantity();
                int bookId = cartDetail.getBookDetails().getId();
                bookRepo.updateBookQuantity(bookId, quantity);
            }
            return "Order cancel successfully";
        } else {
            throw new BookStoreException("This id is not present");
        }
    }
}

