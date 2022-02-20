package com.deo.microservices.statisticsservice.service;

import com.deo.microservices.statisticsservice.model.Statistics;

import java.util.List;

public interface StatisticsService {

    Statistics create(Statistics statistics);

    Statistics findById(Long id);

    List<Statistics> findAll();

}
