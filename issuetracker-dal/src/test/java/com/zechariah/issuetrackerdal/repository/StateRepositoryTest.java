package com.zechariah.issuetrackerdal.repository;

import com.zechariah.issuetrackerdal.model.EquipmentModel;
import com.zechariah.issuetrackerdal.model.State;
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
class StateRepositoryTest {

    @Autowired
    private StateRepository repository;
    private State state;

    @BeforeEach
    public void setUp(){
        state = new State("GenSet", "working");
    }

    @AfterEach
    public void tearDown(){
        repository.deleteAll();
        state = null;
    }

    @Test
    public void givenStateSaveState(){
        repository.save(state);
        State fetchEquipment = repository.findById(state.getId()).get();
        assertEquals("GenSet", fetchEquipment.getName());
    }

    @Test
    public void StateList(){
        State state1 = new State("BadMan", "blown");
        State state2 = new State("Running", "pending");

        repository.save(state1);
        repository.save(state2);

        List<State> states = (List<State>) repository.findAll();
        assertEquals("Running", state2.getName());
    }

    @Test
    public void specificState(){
        State equipment1 = new State("Stateless", "blown");
        State equipmentModel = repository.save(equipment1);

        Optional<State> optional = repository.findById(equipmentModel.getId());
        System.out.println(optional);
        assertEquals(equipmentModel.getId(), optional.get().getId());
        assertEquals(equipmentModel.getName(), optional.get().getName());
    }

    @Test
    public void deletingState(){
        State state = new State("uhmm exeh", "wilding");
        repository.save(state);
        repository.deleteById(state.getId());
        Optional optional = repository.findById(state.getId());

        assertEquals(Optional.empty(), optional);
    }

}