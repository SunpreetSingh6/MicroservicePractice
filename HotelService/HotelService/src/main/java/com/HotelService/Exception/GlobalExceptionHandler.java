package com.HotelService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourseNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleResourseNotFoundException(ResourseNotFoundException ex){
        Map<String,Object> res = new HashMap<>();
        res.put("message",ex.getMessage());
        res.put("status", HttpStatus.NOT_FOUND);
        res.put("success",true);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

}
