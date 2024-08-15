package com.example.user_service.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@Document
public class User {
    @Id
    @Column(length = 36)
    private String userId;
    private String userName;
    private String email;
    private String password;

    List<Restaurant>favouriteList;
    List<Product> cartItems;
    public User(){

    }

    public User(String userId, String userName, String email, String password) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public List<Restaurant> getFavouriteList() {
        return favouriteList;
    }

    public void setFavouriteList(List<Restaurant> favouriteList) {
        this.favouriteList = favouriteList;
    }

    public List<Product> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<Product> cartItems) {
        this.cartItems = cartItems;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", favouriteList=" + favouriteList +
                ", cartItems=" + cartItems +
                '}';
    }
}
