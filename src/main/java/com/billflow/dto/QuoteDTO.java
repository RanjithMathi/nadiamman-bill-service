package com.billflow.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;


import lombok.Data;

@Data
public class QuoteDTO {

    private Long id;
    private Long clientId;
    private String status;
    private String description;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}