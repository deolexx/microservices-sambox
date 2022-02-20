package com.deo.microservices.statisticsservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "Statistics")
@Getter
@Setter
@EqualsAndHashCode
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Statistics {

    @Id
    private Long id;
    private String message;
    private LocalDateTime dateTime;
}


