package com.example.user_service.repository;

import com.example.user_service.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findByEmail(String email);
}
