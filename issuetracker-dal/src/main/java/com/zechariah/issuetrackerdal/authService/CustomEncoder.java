package com.zechariah.issuetrackerdal.authService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomEncoder {
    private  PasswordEncoder passwordEncoder;

    public CustomEncoder(){
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

}
