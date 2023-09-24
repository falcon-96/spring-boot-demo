package com.self.probe.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.self.probe.model.Droid;
import com.self.probe.model.DroidList;
import com.self.probe.service.ApplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MainControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    public void testHello() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/hello").contentType(MediaType.APPLICATION_JSON)).andReturn();

        String content = result.getResponse().getContentAsString();

        assert (content.equals("Hello World"));
    }

    @Test
    public void testCreateDroidWithName() throws Exception {
        String testString = "test";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/createDroid")
                        .param("name", testString))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();

        assert (content.equals("Successfully Added: [" + testString + "]"));

    }

    @Autowired
    private ApplicationService appService;


    @Autowired
    private DroidList droidList;


    @Test
    public void testDroidList() throws Exception {
        String testString1 = "test1";
        String testString2 = "test2";
        mvc.perform(MockMvcRequestBuilders.post("/createDroid")
                        .param("name", testString1))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        mvc.perform(MockMvcRequestBuilders.post("/createDroid")
                        .param("name", testString2))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();


        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/showAllDroids"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        DroidList droidList = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<DroidList>() {
        });
        assert (droidList.getDroidList().toString().contains("test1"));
        assert (droidList.getDroidList().toString().contains("test2"));

    }

}