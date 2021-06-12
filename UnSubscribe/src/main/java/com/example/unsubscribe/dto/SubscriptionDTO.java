package com.example.unsubscribe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionDTO {
    private Integer id;
    private String productName;
    private String productLink;
    private Double price;
    private LocalDate expirationDate;
    private Integer timeInterval;
}
