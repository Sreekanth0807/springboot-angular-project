package com.example.UserAuthentication.repository;

import com.example.UserAuthentication.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    User findByEmailAndPassword(String email, String password);
}
