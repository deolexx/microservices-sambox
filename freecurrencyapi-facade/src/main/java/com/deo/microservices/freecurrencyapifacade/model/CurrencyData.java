package com.deo.microservices.freecurrencyapifacade.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CurrencyData {
    private String query;
    private String data;
}
