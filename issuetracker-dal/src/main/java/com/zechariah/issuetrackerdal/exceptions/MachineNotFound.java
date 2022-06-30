package com.zechariah.issuetrackerdal.exceptions;

public class MachineNotFound extends RuntimeException {

    public MachineNotFound(Long id) {
        super("Could not find Machine " + id);
    }
}
