package com.zechariah.issuetrackerdal.repository;

import com.zechariah.issuetrackerdal.model.EquipmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<EquipmentModel, Long> {

}
