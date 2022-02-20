package com.deo.microservices.statisticsservice.repository;

import com.deo.microservices.statisticsservice.model.Statistics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsRepository extends MongoRepository<Statistics, Long> {
}
