package com.example.UserAuthentication.service;

import com.example.UserAuthentication.Exception.InvalidCredentialException;
import com.example.UserAuthentication.Exception.UserAlreadyExistException;
import com.example.UserAuthentication.domain.User;

public interface IUserService {
    User saveUser(User user) throws UserAlreadyExistException;
    User findByEmailAndPassword(String email,String password) throws InvalidCredentialException;
}
