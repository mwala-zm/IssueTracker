package com.zechariah.issuetrackerdal.repository;

import com.zechariah.issuetrackerdal.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

}
