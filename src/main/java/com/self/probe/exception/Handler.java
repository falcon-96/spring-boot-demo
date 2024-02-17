package com.self.probe.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class Handler {

    private static final Logger log = LoggerFactory.getLogger(Handler.class);

    private final JdbcClient jdbcClient;

    public Handler(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidations(MethodArgumentNotValidException c) {

        String errors = c.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ":" + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", ", "[", "]"));


        String query = "INSERT INTO RESPONSE_ERRORS(MESSAGE, DATETIME) VALUES(?,?)";
        jdbcClient.sql(query)
                .param(errors)
                .param(LocalDateTime.now())
                .update();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleJdbcErrors(DataAccessException e) {
        log.error("JDBC ERROR:", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error in query");

    }
}
