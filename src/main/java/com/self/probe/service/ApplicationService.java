package com.self.probe.service;

import com.self.probe.model.Droid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Service
public class ApplicationService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public ResponseEntity<String> createNewDroid(Droid droid) {

        try {
            jdbcTemplate.update("INSERT INTO DROIDS(NAME, MODEL) VALUES(?,?)", droid.name(), droid.model());
        } catch (DataAccessException e) {
            throw e;
        }

        return ResponseEntity.status(HttpStatus.OK).body("Successfully Added: [" + droid.name() + "]");

    }

    public ResponseEntity<?> getAllDroids() {
        String sql = "SELECT * FROM DROIDS";
        try {
            List<Map<String, Object>> droids = jdbcTemplate.queryForList(sql);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(droids);
        } catch (DataAccessException e) {
            throw e;
        }

    }

    public ResponseEntity<String> updateName(Droid droid) {
        String checkSql = "SELECT * FROM DROIDS WHERE NAME = ?";
        String updateSql = "UPDATE DROIDS SET MODEL=? WHERE NAME=?";

        try {

            int updatedRows = jdbcTemplate.update(updateSql, droid.model(), droid.name());
            if (updatedRows > 0) {
                return ResponseEntity.status(HttpStatus.OK).body(updatedRows + " row/s updated Successfully!");
            } else {
                throw new EmptyResultDataAccessException("No records founds", 1);
            }

        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No droid with :" + droid.name());
        } catch (DataAccessException e) {
            throw e;
        }
    }

    public ResponseEntity<String> deleteDroid(String name) {
        String deleteSql = "DELETE FROM DROIDS WHERE NAME = ?";

        try {

            int deletedRows = jdbcTemplate.update(deleteSql, name);

            if (deletedRows > 0) {
                return ResponseEntity.status(HttpStatus.OK).body(deletedRows + " row/s updated Successfully!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No droid with :" + name);
            }
        } catch (DataAccessException e) {
            throw e;
        }

    }

}
