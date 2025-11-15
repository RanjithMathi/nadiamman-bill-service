package com.billflow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class InvoiceRequest {
    private String invoiceNumber;
    
    @NotNull(message = "Client ID is required")
    private Long clientId;
    
    @NotNull(message = "Date is required")
    private LocalDateTime date;

//    @NotNull(message = "Due date is required")
    private LocalDateTime dueDate;
    
    @NotNull(message = "Items are required")
    private List<InvoiceItemRequest> items;
    
    private BigDecimal tax = BigDecimal.ZERO;
    private BigDecimal discount = BigDecimal.ZERO;
    private String status = "pending";
    private String notes;
    
    @Data
    public static class InvoiceItemRequest {
        @NotNull(message = "Product ID is required")
        private Long productId;

        @NotNull(message = "Quantity is required")
        private Integer quantity;

        @NotNull(message = "Price is required")
        private BigDecimal price;

        private List<String> serialNumbers;
    }
}
