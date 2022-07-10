package com.zechariah.issuetrackerdal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zechariah.issuetrackerdal.model.EquipmentModel;
import com.zechariah.issuetrackerdal.repository.EquipmentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
class EquipmentControllerTest {

    @Mock
    private EquipmentRepository repository;
    private EquipmentModel equipment;
    private List<EquipmentModel> equipmentList;

    @InjectMocks
    private EquipmentController equipmentController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        equipment = new EquipmentModel("uhmm exeh", "wilding", "chamba Valley");
        mockMvc = MockMvcBuilders.standaloneSetup(equipmentController).build();

    }

    @AfterEach
    void tearDown() {
        equipment = null;
    }



    @Test
    public void postMethod() throws Exception {
        when(repository.save(equipment)).thenReturn(equipment);
            mockMvc.perform(post("/equipment")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(equipment)))
                    .andExpect(status().isCreated());
    }

    @Test
    public void getMethod() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/equipment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(equipment)))
                .andDo(MockMvcResultHandlers.print());
        verify(repository.findAll());
    }


    public static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }
        catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
}