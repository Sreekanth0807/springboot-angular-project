package com.example.UserAuthentication.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "User already exist")
public class UserAlreadyExistException extends Exception{
}
