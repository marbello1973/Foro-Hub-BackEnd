package com.forohub.api.excepcion;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleEntityNotFoundException(EntityNotFoundException exception){
        Map<String, Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        response.put("timestamp", new Date());
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("error", "Internal Server Error");
        response.put("message", exception.getMessage());
        response.put("path", "/topico");

        String errorUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/topico/error/{status}")
                .buildAndExpand(status.value())
                .toUriString();
        response.put("url", errorUrl);

        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request){
        Map<String, Object> body = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        body.put("timestamp", new Date());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", "Data Integrity Violation");
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false));

        String errorUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/topico/error/{status}")
                .buildAndExpand(status.value())
                .toUriString();
        body.put("url", errorUrl);

        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException exception, WebRequest request){
        Map<String, Object> body = new HashMap<>();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        body.put("timestamp", new Date());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("message", exception.getMessage());
        body.put("path", request.getDescription(true)); // true para obtener detalles de la URL

        String errorUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/topico/error/{status}")
                .buildAndExpand(status.value())
                .toUriString();
        body.put("url", errorUrl);

        return new ResponseEntity<>(body, status);
    }
}
