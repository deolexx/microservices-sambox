package com.deo.microservices.conversionmultipleservice.repository;

import com.deo.microservices.conversionmultipleservice.model.Currency;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyRepository extends CrudRepository<Currency, String> {
}
