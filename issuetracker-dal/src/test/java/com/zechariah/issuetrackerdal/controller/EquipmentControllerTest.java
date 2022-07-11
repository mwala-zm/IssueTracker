package com.zechariah.issuetrackerdal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zechariah.issuetrackerdal.model.EquipmentModel;
import com.zechariah.issuetrackerdal.repository.EquipmentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EquipmentController.class)
public class EquipmentControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    EquipmentRepository repository;

    EquipmentModel machine1 = new EquipmentModel("GenSet", "New", "Chipata");
    EquipmentModel machine2 = new EquipmentModel("Transformer", "old 89 model", "Chililabombwe");

    @Test
    public void allEquipment() throws Exception {
        List<EquipmentModel> machines = new ArrayList<>(Arrays.asList(machine1, machine2));

        Mockito.when(repository.findAll()).thenReturn(machines);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/patient")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].name", is("Transformer")));
    }

    @Test
    public void getPatientById_success() throws Exception {
        Mockito.when(repository.findById(machine1.getId())).thenReturn(java.util.Optional.of(machine1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/equipment/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("GenSet")));
    }

}