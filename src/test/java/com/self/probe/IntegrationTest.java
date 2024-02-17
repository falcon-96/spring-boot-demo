package com.self.probe;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.self.probe.model.Droid;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
class IntegrationTest {

    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testCreateAValidDroid() throws Exception {
        String droidName = "dummyDroid";
        Droid testItem = new Droid(droidName, "dummyModel");

        String jsonTest = objectMapper.writeValueAsString(testItem);
        String expectedResponse = "Successfully Added: [" + droidName + "]";
        mvc.perform(MockMvcRequestBuilders.post("/api/createDroid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonTest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(expectedResponse))
                .andDo(print());
    }

    @Test
    void testGetListOfDroid() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/showAllDroids"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testUpdateDroid() throws Exception {
        String droidName = "testName";
        Droid testItem = new Droid(droidName, "testModel");

        String jsonTest = objectMapper.writeValueAsString(testItem);
        String expectedResponse = "Successfully Added: [" + droidName + "]";
        mvc.perform(MockMvcRequestBuilders.post("/api/createDroid")
                        .contentType(MediaType.APPLICATION_JSON).content(jsonTest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(expectedResponse))
                .andDo(print());

        Droid updateTestItem = new Droid(testItem.name(), testItem.model() + "!");

        String expectedResponseForUpdate = "1 row/s updated Successfully!";

        jsonTest = objectMapper.writeValueAsString(updateTestItem);
        mvc.perform(MockMvcRequestBuilders.put("/api/editDroid").contentType(MediaType.APPLICATION_JSON).content(jsonTest)).andExpect(status().isOk()).andExpect(content().string(expectedResponseForUpdate));
    }

    @Test
    void testDeleteDroid() throws Exception {
        String droidName = "testName";
        Droid testItem = new Droid(droidName, "testModel");

        String jsonTest = objectMapper.writeValueAsString(testItem);
        String expectedResponse = "Successfully Added: [" + droidName + "]";
        mvc.perform(MockMvcRequestBuilders.post("/api/createDroid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonTest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(expectedResponse))
                .andDo(print());

        String deleteExpectResponse = "1 row/s updated Successfully!";
        mvc.perform(MockMvcRequestBuilders.delete("/api/deleteDroid")
                        .param("name", testItem.name()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testCreationOfDroidWithNullName() throws Exception {
        Droid d = new Droid(null, "test");
        mvc.perform(MockMvcRequestBuilders.post("/api/createDroid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(d)))
                .andDo(print())
                .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    void testCreationOfDroidWithNullModel() throws Exception {
        Droid d = new Droid("Some Name", null);
        mvc.perform(MockMvcRequestBuilders.post("/api/createDroid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(d)))
                .andDo(print())
                .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    void testShowAllDroids() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/showAllDroids"))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
    }
}