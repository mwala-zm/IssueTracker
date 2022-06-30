package com.zechariah.issuetrackerdal.exceptions;

public class InspectionNotFound extends RuntimeException{
    public InspectionNotFound(Long id){
        super("Could not find information on this inspection" + id);
    }
}
