package com.example.user_service.services;

import com.example.user_service.Exception.*;

import com.example.user_service.Proxy.UserProxy;

import com.example.user_service.domain.Product;
import com.example.user_service.domain.Restaurant;
import com.example.user_service.domain.User;
import com.example.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.UUID;
@Service
public class UserServiceImp implements IUserService {
    private UserRepository userRepository;
    private UserProxy userProxy;

    @Autowired
    public UserServiceImp(UserRepository userRepository,UserProxy userProxy) {
        this.userRepository = userRepository;
        this.userProxy=userProxy;
    }


    @Transactional
    @Override
    public User register(String userName,String email, String password) throws UserAlreadyExistException {
        System.out.println("Registering user with email: " + email);
        if(userRepository.findByEmail(email).isPresent())
        {
            throw new UserAlreadyExistException();
        }
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setUserName(userName);
        user.setEmail(email);
        user.setPassword(password);

        User savedUser = userRepository.save(user);
        System.out.println(savedUser);
        if(!savedUser.getUserId().isEmpty())
        {
            ResponseEntity<?> r = userProxy.saveUser(user);
            System.out.println("Saved User: "+r.getBody());

        }

        return savedUser;
    }
    /*@Override
    public User saveUser(User user) throws UserAlreadyExistException {
        System.out.println("Received userName in service layer: " + user);
        if(userRepository.findById(user.getUserId()).isPresent())
        {
            throw new UserAlreadyExistException();
        }
        return userRepository.save(user);
    }*/




    @Override
    public User addFavourites(Restaurant restaurant, String userId) throws FavouriteAlreadyExistException, UserNotFoundException {
        if (userRepository.findById(userId).isEmpty()) {
            throw new UserNotFoundException();
        }
        User user = userRepository.findById(userId).get();
        List<Restaurant> favouriteList = user.getFavouriteList();
        if (favouriteList == null) {
            System.out.println("Add to list");
            favouriteList = Arrays.asList(restaurant);
            user.setFavouriteList(favouriteList);
        } else {
            for (Restaurant f : favouriteList) {
                if (f.getId() == restaurant.getId()) {
                    throw new FavouriteAlreadyExistException();
                }
            }
            favouriteList.add(restaurant);
            user.setFavouriteList(favouriteList);
        }

        return userRepository.save(user);
    }

    @Override
    public User deleteFavourite(int id, String userId) throws UserNotFoundException, FavouriteNotFoundException {
        System.out.println("DElete methoda in service layer");
        if (userRepository.findById(userId).isEmpty()) {
            throw new UserNotFoundException();
        }

        User foundUser = userRepository.findById(userId).get();
        List<Restaurant> favouriteList = foundUser.getFavouriteList();
        boolean favouritePresent = favouriteList.removeIf(x -> x.getId() == id);
        System.out.println("Deleted");
        if (!favouritePresent) {
            throw new FavouriteNotFoundException();
        }
        foundUser.setFavouriteList(favouriteList);
        System.out.println("Deleted");
        return userRepository.save(foundUser);
    }

    @Override
    public List<Restaurant> getAllFavourite(String userId) throws UserNotFoundException, FavouriteNotFoundException {
        if (userRepository.findById(userId).isEmpty()) {
            throw new UserNotFoundException();
        }
        User foundUser = userRepository.findById(userId).get();
        List<Restaurant> favouriteList = foundUser.getFavouriteList();
        if (favouriteList.isEmpty()) {
            throw new FavouriteNotFoundException();
        }
        return favouriteList;
    }

    @Override
    public User addCartItems(Product product, String userId) throws ProductAlreadyExistException, UserNotFoundException {
        System.out.println("add cart methoda in service layer");
        if(userRepository.findById(userId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user=userRepository.findById(userId).get();
        System.out.println("add method in service layer");
        List<Product> cartList=user.getCartItems();
        System.out.println("same add method in service layer");
        if(cartList==null)
        {
            System.out.println("add to cart");
            cartList=Arrays.asList(product);
            user.setCartItems(cartList);
        }
        else {
            System.out.println("sameeeee method in service layer");
            for(Product p:cartList)
            {
                if(p.getProductId().equals(product.getProductId()))
                {
                    System.out.println("456 method in service layer");
                    throw new ProductAlreadyExistException();
                }

            }
            System.out.println("124 method in service layer");
            cartList.add(product);
            user.setCartItems(cartList);
        }
        System.out.println("124 method in service layer");
        return userRepository.save(user);
    }

    @Override
    public User removeCartItems(String productId, String userId) throws ProductNotFoundException, UserNotFoundException {
        System.out.println(productId+"1st");

        if(userRepository.findById(userId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        System.out.println(productId+"2nd");
        User user= userRepository.findById(userId).get();
        System.out.println(productId+"3rd");
        List<Product>cartList=user.getCartItems();
        System.out.println(productId+"4th");
        boolean cartPresent = cartList.removeIf(x -> x.getProductId().equals(productId));
        System.out.println(productId+"5th");
        if(!cartPresent)
        {
            throw new ProductNotFoundException();
        }
        System.out.println(productId+"6th");
        user.setCartItems(cartList);
        System.out.println(productId+"7th");
        return userRepository.save(user);
    }

    @Override
    public List<Product> getCartItems(String userId) throws ProductNotFoundException, UserNotFoundException {
        System.out.println(userId+"1");
        if(userRepository.findById(userId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        System.out.println(userId+"2");
        User user=userRepository.findById(userId).get();
        System.out.println(userId+"3");
        List<Product>cartList=user.getCartItems();
        System.out.println(userId+"4");
        if(cartList.isEmpty())
        {
            throw new ProductNotFoundException();
        }
        System.out.println(userId+"5");
        return cartList;
    }

    @Override
    public User clearCart(String userId) throws ProductNotFoundException, UserNotFoundException {
        if(userRepository.findById(userId).isEmpty())
        {
            throw new UserNotFoundException();
        }

        User foundUser=userRepository.findById(userId).get();

        List<Product> cart=foundUser.getCartItems();
        if(cart.isEmpty())
        {
            throw new ProductNotFoundException();
        }
        foundUser.setCartItems(new ArrayList<>());
        return userRepository.save(foundUser);
    }
}

