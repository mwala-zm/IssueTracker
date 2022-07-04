package com.zechariah.issuetrackerdal.repository;

import com.zechariah.issuetrackerdal.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Long> {
}
