package com.zechariah.issuetrackerdal.repository;

import com.zechariah.issuetrackerdal.model.EquipmentModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class EquipmentRepositoryTest {

    @Autowired
    private EquipmentRepository repository;

    private EquipmentModel equipment;

    @BeforeEach
    public void setUp(){
        equipment = new EquipmentModel("GenSet", "working", "South London");
    }

    @AfterEach
    public void tearDown(){
        repository.deleteAll();
        equipment = null;
    }

    @Test
    public void givenMachineSaveMachine(){
        repository.save(equipment);
        EquipmentModel fetchEquipment = repository.findById(equipment.getId()).get();
//        System.out.println(fetchEquipment + " our solution");
        assertEquals("GenSet", fetchEquipment.getName());
    }

    @Test
    public void equipmentList(){
        EquipmentModel equipment1 = new EquipmentModel("BadMan", "blown", "chili boarder");
        EquipmentModel equipment2 = new EquipmentModel("Hanzo", "pending", "Japanr");

        repository.save(equipment1);
        repository.save(equipment2);

        List<EquipmentModel> equipmentModels = (List<EquipmentModel>) repository.findAll();
//        System.out.println(equipmentModels + "this is my OutPut");
        assertEquals("Hanzo", equipment2.getName());
    }

    @Test
    public void specificMachine(){
        EquipmentModel equipment1 = new EquipmentModel("BadMan", "blown", "chili boarder");
        EquipmentModel equipmentModel = repository.save(equipment1);

        Optional<EquipmentModel> optional = repository.findById(equipmentModel.getId());
//        System.out.println(optional + "What I'm looking for");
        assertEquals(equipmentModel.getId(), optional.get().getId());
        assertEquals(equipmentModel.getName(), optional.get().getName());
    }

    @Test
    public void deletingMachine(){
        EquipmentModel equipment = new EquipmentModel("uhmm exeh", "wilding", "chamba Valley");
        repository.save(equipment);
        repository.deleteById(equipment.getId());
        Optional optional = repository.findById(equipment.getId());

        assertEquals(Optional.empty(), optional);
    }
}