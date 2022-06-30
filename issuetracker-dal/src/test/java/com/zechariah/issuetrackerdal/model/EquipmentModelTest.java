package com.zechariah.issuetrackerdal.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentModelTest {


    @Test
    void sholudNotBeNull(){
        EquipmentModel equipment = new EquipmentModel();
        assertEquals("", equipment.getName());
    }

}