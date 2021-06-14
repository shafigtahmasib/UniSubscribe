package com.example.unsubscribe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Provided format is wrong")
public class WrongFormatException extends Exception {

}