package com.self.probe.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class ServiceTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @Test
    void testCreateNewDroidWithEmptyName(){

    }
}
