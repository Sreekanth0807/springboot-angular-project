package com.example.user_service.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Favourite already exist")
public class FavouriteAlreadyExistException extends Exception{
}
