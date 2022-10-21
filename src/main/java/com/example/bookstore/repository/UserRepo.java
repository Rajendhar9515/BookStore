package com.example.bookstore.repository;

import com.example.bookstore.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<UserDetails, Integer> {

    @Query(value = "SELECT * FROM bookstore.user_details where userid = :userid", nativeQuery = true)
    List<UserDetails> getUserId(Integer userid);
}
