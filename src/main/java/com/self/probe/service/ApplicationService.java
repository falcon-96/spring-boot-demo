package com.self.probe.service;

import com.self.probe.model.Droid;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ApplicationService {
    private final JdbcClient jdbcClient;

    public ApplicationService(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public ResponseEntity<String> createNewDroid(Droid droid) {
        int rows = jdbcClient.sql("INSERT INTO DROIDS(NAME, MODEL) VALUES(?,?)")
                .param(droid.name())
                .param(droid.model())
                .update();
        if (rows >= 1) {
            return ResponseEntity.status(HttpStatus.OK).body("Successfully Added: [" + droid.name() + "]");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in adding:" + droid.name());
        }
    }

    public ResponseEntity<List<Map<String, Object>>> getAllDroids() {
        String getAllDroidSql = "SELECT * FROM DROIDS";
        List<Map<String, Object>> droids = jdbcClient.sql(getAllDroidSql)
                .query().listOfRows();

        return ResponseEntity.status(HttpStatus.OK)
                .body(droids);
    }

    public ResponseEntity<String> updateName(Droid droid) {
        String updateSql = "UPDATE DROIDS SET MODEL=? WHERE NAME=?";

        try {
            int updatedRows = jdbcClient.sql(updateSql)
                    .param(droid.model())
                    .param(droid.name())
                    .update();
            if (updatedRows > 0) {
                return ResponseEntity.status(HttpStatus.OK).body(updatedRows + " row/s updated Successfully!");
            } else {
                throw new EmptyResultDataAccessException("No records founds", 1);
            }
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No droid with :" + droid.name());
        }
    }

    public ResponseEntity<String> deleteDroid(String name) {
        String deleteSql = "DELETE FROM DROIDS WHERE NAME = ?";
        int deletedRows = jdbcClient.sql(deleteSql)
                .param(name)
                .update();
        if (deletedRows > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(deletedRows + " row/s updated Successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No droid with :" + name);
        }
    }
}
