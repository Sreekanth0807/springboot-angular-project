package com.example.user_service.controller;


import com.example.user_service.Exception.*;


import com.example.user_service.domain.Product;
import com.example.user_service.domain.Restaurant;

import com.example.user_service.domain.User;

import com.example.user_service.services.UserServiceImp;
import io.jsonwebtoken.Claims;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("api/v2/")
public class UserRestaurantController {
    private UserServiceImp userService;
    private ResponseEntity responseEntity;
    @Autowired
    public UserRestaurantController(UserServiceImp userService)
    {
        this.userService=userService;

    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestParam("userName") String userName, @RequestParam("email") String email,@RequestParam String password )throws  UserAlreadyExistException {
        System.out.println("Received userName: " + userName);
        System.out.println("Received email: " + email);
        System.out.println("Received password: " + password);
        try
        {
            //userService.register(user);
            responseEntity=new ResponseEntity<>(userService.register(userName,email,password),HttpStatus.CREATED);
        }
        catch (UserAlreadyExistException e)
        {
            System.out.println("UserAlreadyExistException: " + e.getMessage());
            responseEntity = new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Exception: " + e.getMessage());
            responseEntity=get500Response(e);
        }
        return responseEntity;
    }
   /* @PostMapping("/saveUser")
    public ResponseEntity save(@RequestBody User user) throws UserAlreadyExistException
    {
        System.out.println("Received userName: " + user);


        try
        {
            responseEntity=new ResponseEntity<>(userService.saveUser(user),HttpStatus.OK);
        }
        catch(UserAlreadyExistException e)
        {
            throw new UserAlreadyExistException();
        }
        catch(Exception e)
        {
            responseEntity=new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }*/


    @PostMapping("/users/addFavourite")
    public ResponseEntity addFavourite(@RequestBody Restaurant restaurant, HttpServletRequest request) throws UserNotFoundException, FavouriteAlreadyExistException {
        try
        {
            System.out.println(restaurant);
            System.out.println(request);
            responseEntity=new ResponseEntity<>(userService.addFavourites(restaurant,getIdFromClaims(request)),HttpStatus.OK);
            System.out.println("added");
        }
        catch(UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        catch (FavouriteAlreadyExistException e)
        {
            throw new FavouriteAlreadyExistException();
        }
        catch (Exception e)
        {
            responseEntity=get500Response(e);
        }
        return responseEntity;
    }

    @DeleteMapping("users/deleteFavourite/{id}")
    public  ResponseEntity deleteFavourites(@PathVariable int id,HttpServletRequest request) throws UserNotFoundException, FavouriteNotFoundException
    {
        System.out.println(id);
        try
        {
            responseEntity=new ResponseEntity<>(userService.deleteFavourite(id,getIdFromClaims(request)),HttpStatus.OK);
        }
        catch(UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        catch(FavouriteNotFoundException e)
        {
            throw new FavouriteNotFoundException();
        }
        catch(Exception e)
        {
            responseEntity=get500Response(e);
        }
        return responseEntity;
    }

    @GetMapping("/users/favourite")
    public ResponseEntity getAllFavourites(HttpServletRequest request) throws UserNotFoundException,FavouriteNotFoundException
    {
        try
        {
            responseEntity=new ResponseEntity<>(userService.getAllFavourite(getIdFromClaims(request)),HttpStatus.OK);
        }
        catch(UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        catch(FavouriteNotFoundException e)
        {
            throw new FavouriteNotFoundException();
        }
        catch(Exception e)
        {
            responseEntity=get500Response(e);
        }
        return responseEntity;
    }
    @PostMapping("/products/addCart")
    public ResponseEntity addCart(@RequestBody Product product,HttpServletRequest request) throws UserNotFoundException, ProductAlreadyExistException
    {
        System.out.println(product);
        try
        {
            responseEntity=new ResponseEntity(userService.addCartItems(product,getIdFromClaims(request)),HttpStatus.CREATED);
        }
        catch (UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        catch(ProductAlreadyExistException e)
        {
            throw new ProductAlreadyExistException();
        }
        catch(Exception e)
        {
            responseEntity=get500Response(e);
        }
        return responseEntity;
    }
    @DeleteMapping("products/deleteCart/{productId}")
    public ResponseEntity deleteCart(@PathVariable String productId,HttpServletRequest request) throws UserNotFoundException,ProductNotFoundException
    {
        System.out.println(productId);

        try
        {
            responseEntity=new ResponseEntity(userService.removeCartItems(productId,getIdFromClaims(request)),HttpStatus.OK);
        }
        catch(UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        catch (ProductNotFoundException e)
        {
            throw new ProductNotFoundException();
        }
        catch(Exception e)
        {
            responseEntity=get500Response(e);
        }
        return responseEntity;
    }

    @GetMapping("products/getCart")
    public ResponseEntity getCart(HttpServletRequest request) throws ProductNotFoundException,UserNotFoundException
    {
        try
        {
            responseEntity=new ResponseEntity<>(userService.getCartItems(getIdFromClaims(request)),HttpStatus.OK);
        }
        catch (UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        catch(ProductNotFoundException e)
        {
            throw new ProductNotFoundException();
        }
        catch(Exception e)
        {
            responseEntity=get500Response(e);
        }
        return responseEntity;
    }

    @DeleteMapping("/products/clearCart")
    public ResponseEntity clearCart(HttpServletRequest request) throws ProductNotFoundException,UserNotFoundException
    {
        System.out.println(request+"from clearcart");
        try
        {
            responseEntity=new ResponseEntity<>(userService.clearCart(getIdFromClaims(request)),HttpStatus.OK);
        }
        catch (UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        catch(ProductNotFoundException e)
        {
            throw new ProductNotFoundException();
        }
        catch(Exception e)
        {
            responseEntity=get500Response(e);
        }
        return responseEntity;
    }

    private String getIdFromClaims(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        return claims.getSubject();
    }

    private ResponseEntity<String> get500Response(Exception ex) {
        return new ResponseEntity<>(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
