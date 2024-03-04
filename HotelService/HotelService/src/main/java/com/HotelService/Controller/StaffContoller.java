package com.HotelService.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/staffs")
public class StaffContoller {

    @GetMapping
    public ResponseEntity<List<String>> getAllStaff(){
        return ResponseEntity.status(HttpStatus.OK).body(Arrays.asList("A","B","C"));
    }


}
