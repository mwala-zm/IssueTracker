package com.zechariah.issuetrackerdal.repository;

import com.zechariah.issuetrackerdal.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository <User, Long> {


}
