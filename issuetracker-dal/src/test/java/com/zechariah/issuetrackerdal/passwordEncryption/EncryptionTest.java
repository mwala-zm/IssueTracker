package com.zechariah.issuetrackerdal.passwordEncryption;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncryptionTest {

    @Test
    public void encrypt(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("asdfasdf"));
    }
}
