package com.deo.microservices.statisticsservice.controller;

import com.deo.microservices.statisticsservice.model.Statistics;
import com.deo.microservices.statisticsservice.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StatisticsController {
    @Autowired
    StatisticsService statisticsService;

    @PostMapping("/statistics")
    public ResponseEntity<Statistics> createNew(@RequestBody Statistics statistics) {
        return new ResponseEntity<>(statisticsService.create(statistics), HttpStatus.CREATED);
    }

    @GetMapping("/statistics")
    public ResponseEntity<List<Statistics>> findAll() {
        return new ResponseEntity<>(statisticsService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/statistics/{id}")
    public ResponseEntity<Statistics> findById(@PathVariable Long id) {
        return new ResponseEntity<>(statisticsService.findById(id), HttpStatus.OK);
    }


}
