package com.zechariah.issuetrackerdal.authService;

import com.zechariah.issuetrackerdal.model.UserModel;
import com.zechariah.issuetrackerdal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> userOpt = userRepository.findByUsername(username);

        return userOpt.orElseThrow(() -> new UsernameNotFoundException("Invalid credentials"));
    }
}
