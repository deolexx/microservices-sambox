package com.deo.microservices.conversionmultipleservice.repository;

import com.deo.microservices.conversionmultipleservice.model.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, String> {

}
