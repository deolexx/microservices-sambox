package com.deo.microservices.conversionmultipleservice.model;

import org.springframework.data.redis.core.RedisHash;

import java.math.BigDecimal;
@RedisHash("Currency")
public class Currency {
    private String id;
    private String code;
    private String currencyName;
    private BigDecimal conversionMultiple;
}

