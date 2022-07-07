package com.zechariah.issuetrackerdal.repository;

import com.zechariah.issuetrackerdal.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
