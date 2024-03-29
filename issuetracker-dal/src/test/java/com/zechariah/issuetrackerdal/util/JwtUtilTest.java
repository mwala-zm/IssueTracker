package com.zechariah.issuetrackerdal.util;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class JwtUtilTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void notAllowedAccess() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/test")).andExpect(status().isUnauthorized());
    }


    @Test
    public void allowedAccess() throws Exception{
        String username = "mwiza";
        String password = "123456789";

        String body = "{\"username\": \"" + username + "\", \"password\":\""+ password + "\"}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
                .content(body))
                .andExpect(status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString();
        response = response.replace("{\"access_token\": \"", "");
        String token = response.replace("\"}", "");

        mvc.perform(MockMvcRequestBuilders.get("/test")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

}