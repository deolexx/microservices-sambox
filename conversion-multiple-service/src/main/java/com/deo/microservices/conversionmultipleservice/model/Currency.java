package com.deo.microservices.conversionmultipleservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.math.BigDecimal;

@RedisHash("Currency")
@Getter
@Setter
@EqualsAndHashCode(exclude = "conversionMultiple")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Currency implements Serializable {

    @Id
    private String id;
    private String code;
    private String currencyName;
    private BigDecimal conversionMultiple;
}

