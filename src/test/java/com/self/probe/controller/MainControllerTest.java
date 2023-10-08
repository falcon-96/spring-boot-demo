package com.self.probe.controller;

import com.self.probe.model.Droid;
import com.self.probe.service.ApplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
class MainControllerTest {

    @Autowired
    MockMvc mvc;
    @Autowired
    private ApplicationService appService;

    @Test
    public void testCreationOfDroidWithNullName() throws Exception {
        Droid d = new Droid(null, "test");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/createDroid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(d)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void testCreationOfDroidWithNullModel() throws Exception {
        Droid d = new Droid("Some Name", null);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/createDroid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(d)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void testCreateDroidWithName() throws Exception {
        Droid d = new Droid("TestName", "TestModel");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/createDroid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(d)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    public void testShowAllDroids() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/showAllDroids"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testUpdateDroid() throws Exception {
        Droid d = new Droid("TESTNAME", "TESTMODEL");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put("/api/editDroid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(d)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

    }


    @Test
    public void testDeleteDroid() throws Exception {
        Droid d = new Droid("TESTNAME", "TESTMODEL");

        //restTemplate.postForObject("http://localhost:8080/createDroid", d);
        mvc.perform(MockMvcRequestBuilders.post("/api/createDroid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(d)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete("/api/deleteDroid")
                        .param("name", d.name()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }


}