package com.deo.microservices.conversionmultipleservice.repository;

import com.deo.microservices.conversionmultipleservice.model.Currency;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrencyRepository {

    private static final String HASH_KEY = "Currency";
    @Autowired
    private RedisTemplate<String, Currency> template;

    public Currency save(Currency currency) {
        template.opsForHash().put(HASH_KEY,currency.getId(), currency);
        return currency;
    }

    public Object findById(String id) {
        return  template.opsForHash().get(HASH_KEY,id);
    }
}
