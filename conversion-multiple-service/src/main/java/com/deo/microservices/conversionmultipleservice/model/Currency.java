package com.deo.microservices.conversionmultipleservice.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.math.BigDecimal;

@RedisHash("Currency")
@Getter
@Setter
@EqualsAndHashCode(exclude = "conversionMultiple")
public class Currency {
    private String id;
    private String code;
    private String currencyName;
    private BigDecimal conversionMultiple;
}

