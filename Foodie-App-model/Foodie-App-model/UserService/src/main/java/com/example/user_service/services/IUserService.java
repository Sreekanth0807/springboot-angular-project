package com.example.user_service.services;

import com.example.user_service.Exception.*;

import com.example.user_service.domain.Product;
import com.example.user_service.domain.Restaurant;
import com.example.user_service.domain.User;

import java.util.List;

public interface IUserService {
    User register(String userName,String email, String password) throws UserAlreadyExistException;
    //User saveUser(User user) throws UserAlreadyExistException;

    User addFavourites(Restaurant restaurant, String userId) throws FavouriteAlreadyExistException, UserNotFoundException;

    User deleteFavourite(int id, String userId) throws UserNotFoundException, FavouriteNotFoundException;

    List<Restaurant> getAllFavourite(String userId) throws UserNotFoundException, FavouriteNotFoundException;

    User addCartItems(Product product, String userId) throws ProductAlreadyExistException,UserNotFoundException;

    User removeCartItems(String productId, String userId) throws ProductNotFoundException,UserNotFoundException;
    List<Product>getCartItems(String userId) throws ProductNotFoundException,UserNotFoundException;
    User clearCart(String userId) throws ProductNotFoundException,UserNotFoundException;
}
