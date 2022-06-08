package com.zechariah.issuetrackerdal.repository;

import com.zechariah.issuetrackerdal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
