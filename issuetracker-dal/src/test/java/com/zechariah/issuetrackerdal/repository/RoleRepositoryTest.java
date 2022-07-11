package com.zechariah.issuetrackerdal.repository;


import com.zechariah.issuetrackerdal.model.EquipmentModel;
import com.zechariah.issuetrackerdal.model.Role;
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
class RoleRepositoryTest {

    @Autowired
    private RoleRepository repository;

    private Role role;

    @BeforeEach
    public void setUp(){
        role = new Role("Higher", "Supervisor");
    }

    @AfterEach
    public void tearDown(){
        repository.deleteAll();
        role = null;
    }

    @Test
    public void givenRoleSaveRole(){
        repository.save(role);
        Role fetchRole = repository.findById(role.getId()).get();
        assertEquals("Supervisor", fetchRole.getAuthority());
    }

    @Test
    public void MachineList(){
        Role role1 = new Role("BadMan", "blown");
        Role role2 = new Role("Hanzo", "pending");

        repository.save(role1);
        repository.save(role2);

        List<Role> equipmentModels = (List<Role>) repository.findAll();
        assertEquals("pending", role2.getAuthority());
    }

    @Test
    public void specificMachine(){
        Role role1 = new Role("BadMan", "blown");
        Role role = repository.save(role1);

        Optional<Role> optional = repository.findById(role.getId());
        assertEquals(role.getId(), optional.get().getId());
        assertEquals(role.getAuthority(), optional.get().getAuthority());
    }

    @Test
    public void deletingMachine(){
        Role role = new Role("uhmm exeh", "wilding");
        repository.save(role);
        repository.deleteById(role.getId());
        Optional optional = repository.findById(role.getId());

        assertEquals(Optional.empty(), optional);
    }

}