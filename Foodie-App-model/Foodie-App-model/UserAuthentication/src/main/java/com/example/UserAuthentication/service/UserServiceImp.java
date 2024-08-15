package com.example.UserAuthentication.service;

import com.example.UserAuthentication.Exception.InvalidCredentialException;
import com.example.UserAuthentication.Exception.UserAlreadyExistException;
import com.example.UserAuthentication.domain.User;
import com.example.UserAuthentication.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements IUserService{
    private UserRepository userRepository;
    public UserServiceImp(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }
    @Override
    public User saveUser(User user) throws UserAlreadyExistException {

        if(userRepository.findById(user.getUserId()).isPresent())
        {
            throw new UserAlreadyExistException();
        }
        System.out.println("Received userName in service layer: " + user);

        return userRepository.save(user);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws InvalidCredentialException {
        System.out.println("Received email i service: " + email);
        System.out.println("Received password: " + password);
        User foundUser = userRepository.findByEmailAndPassword(email,password);
        if(foundUser==null)
        {
            throw new InvalidCredentialException();
        }
        return foundUser;
    }
}
