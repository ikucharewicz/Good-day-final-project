//package com.example.goodday.controller;
//
//import java.util.List;
//
//import com.example.goodday.service.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//public interface UserRepository extends JpaRepository<User, Long> {
//
//    @Query("SELECT u from User u where e-mail like :q")
//    List<User> findByEmailPart(@Param("q") String mailLike);
//
//
//}