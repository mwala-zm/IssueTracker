package com.zechariah.issuetrackerdal.exceptions;

public class UserNotFound extends RuntimeException{

    public UserNotFound(Long id){
        super("Sorry, It seems the user with ID:" + id + "is not in our database" );
    }
}
