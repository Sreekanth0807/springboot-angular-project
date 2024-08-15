package com.example.UserAuthentication.Controller;


import com.example.UserAuthentication.Exception.InvalidCredentialException;
import com.example.UserAuthentication.Exception.UserAlreadyExistException;
import com.example.UserAuthentication.Security.ISecurityTokenGenerator;
import com.example.UserAuthentication.domain.User;
import com.example.UserAuthentication.service.IUserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class  UserController {
    private IUserService userService;
    private ResponseEntity responseEntity;
    private ISecurityTokenGenerator securityTokenGenerator;
    public UserController(IUserService userService,ISecurityTokenGenerator securityTokenGenerator)
    {
        this.userService=userService;
        this.securityTokenGenerator=securityTokenGenerator;
    }
    @PostMapping("/saveUser")
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
    }
    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestParam("email") String email,@RequestParam String password) throws InvalidCredentialException
    {
        System.out.println("Received email: " + email);
        System.out.println("Received password: " + password);
        try
        {
            User founUser = userService.findByEmailAndPassword(email,password);
            responseEntity=new ResponseEntity<>(securityTokenGenerator.createToken(founUser), HttpStatus.OK);

        }
        catch (InvalidCredentialException e)
        {
            throw new InvalidCredentialException();
        }
        catch(Exception e)
        {
            responseEntity=new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
//this is a nwe comment to upload this project to git hub
