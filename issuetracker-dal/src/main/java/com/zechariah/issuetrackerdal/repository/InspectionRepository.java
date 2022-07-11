package com.zechariah.issuetrackerdal.repository;

import com.zechariah.issuetrackerdal.model.InspectionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InspectionRepository extends JpaRepository<InspectionModel, Long> {
}
