package com.zechariah.issuetrackerdal.repository;

import com.zechariah.issuetrackerdal.model.Role;
import com.zechariah.issuetrackerdal.model.UserModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    private UserModel user;
    private Role role;

    @BeforeEach
    public void setUp() {

        user = new UserModel(1L, "mwala", "123456789", role);
    }

    @AfterEach
    public void tearDown() {
        repository.deleteAll();
        user = null;
    }

    // Saving a User
    @Test
    public void givenUserReturnUser(){
        repository.save(user);
        UserModel fetchedUser = repository.findById(user.getId()).get();

        assertEquals(1, fetchedUser.getId());
    }

    // Getting a list of all users
    @Test
    public void givenUsersReturnUsers(){
        UserModel user = new UserModel(1L,"J.Cole", "asdfasdf", role);
        UserModel userModel = new UserModel(2L, "Bas", "dreamvile1", role);
        repository.save(user);
        repository.save(userModel);

        List<UserModel> userModelList = (List<UserModel>) repository.findAll();

        assertEquals("J.Cole", userModelList);
    }

}