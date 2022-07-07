package com.zechariah.issuetrackerdal.passwordEncryption;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;


public class EncryptionTest {

    @Test
    public void encryptIsFalse(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String result = passwordEncoder.encode("123456789");

        assertThat(passwordEncoder.matches("", result)).isFalse();
    }

    @Test
    public void encryptMatches(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String result = encoder.encode("123456789");

        assertThat(result.equals("123456789")).isFalse();
        assertThat(encoder.matches("123456789", result)).isTrue();
    }
}
