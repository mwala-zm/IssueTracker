package com.zechariah.issuetrackerdal.model;

import com.zechariah.issuetrackerdal.repository.EquipmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentModelTest {

    @Autowired
    EquipmentRepository repository;

    @Test
    void sholudNotBeNull(){
        EquipmentModel equipment = new EquipmentModel();
        String result = equipment.getName();
        System.out.println(result);
//        assertEquals(null, result);
    }

}