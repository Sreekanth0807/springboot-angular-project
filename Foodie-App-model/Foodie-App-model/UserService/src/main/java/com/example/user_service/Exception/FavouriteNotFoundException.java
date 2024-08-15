package com.example.user_service.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Favourite not found")
public class FavouriteNotFoundException extends Exception {
}
