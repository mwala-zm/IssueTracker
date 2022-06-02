package com.zechariah.issuetrackerdal.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(UserNotFound.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)

	String
	userNotFoundHandler(UserNotFound ex){
        return ex.getMessage();          
	}


}
