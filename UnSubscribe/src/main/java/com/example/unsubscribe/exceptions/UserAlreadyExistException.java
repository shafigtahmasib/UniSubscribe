package com.example.unsubscribe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "There is an account with that email address")
public class UserAlreadyExistException extends Exception {

}
