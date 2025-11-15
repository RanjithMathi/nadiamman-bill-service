package com.billflow.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "battery_serials")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BatterySerial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Serial number is required")
    @Column(nullable = false, unique = true)
    private String serialNumber;

    @NotNull(message = "Product is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_item_id")
    private InvoiceItem invoiceItem;

    @Column(nullable = false)
    private LocalDateTime purchaseDate;

    @Column(nullable = false)
    private LocalDateTime warrantyStartDate;

    @Column(nullable = false)
    private LocalDateTime warrantyEndDate;

    @NotBlank(message = "Warranty status is required")
    @Column(nullable = false)
    private String warrantyStatus = "active"; // active, expired, voided

    // Helper method to check if warranty is active
    public boolean isWarrantyActive() {
        LocalDateTime now = LocalDateTime.now();
        return "active".equals(warrantyStatus) &&
               now.isAfter(warrantyStartDate.minusDays(1)) &&
               now.isBefore(warrantyEndDate.plusDays(1));
    }

    // Helper method to check if warranty has started
    public boolean hasWarrantyStarted() {
        return LocalDateTime.now().isAfter(warrantyStartDate.minusDays(1));
    }

    // Helper method to check if warranty has expired
    public boolean hasWarrantyExpired() {
        return LocalDateTime.now().isAfter(warrantyEndDate);
    }
}