package com.zechariah.issuetrackerdal.util;

import com.zechariah.issuetrackerdal.model.UserModel;

public class AuthUtil {

    public static Boolean hasRole(String role, UserModel user) {
        return user.getAuthorities()
                .stream()
                .filter(auth -> auth.getAuthority().equals(role))
                .count() > 0;
    }
}
