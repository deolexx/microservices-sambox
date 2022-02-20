package com.deo.microservices.statisticsservice.service;

import com.deo.microservices.statisticsservice.model.Statistics;
import com.deo.microservices.statisticsservice.repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    StatisticsRepository statisticsRepository;

    @Override
    public Statistics create(Statistics statistics) {
        return statisticsRepository.save(statistics);
    }

    @Override
    public Statistics findById(Long id) {
        return statisticsRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException();
        });
    }

    @Override
    public List<Statistics> findAll() {
        return statisticsRepository.findAll();
    }
}
