package com.deo.microservices.limitsservice.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Limits {
    private int min;
    private int max;
}
