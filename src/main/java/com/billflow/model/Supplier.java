package com.billflow.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "suppliers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Supplier name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Phone is required")
    @Column(nullable = false)
    private String phone;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true, length = 500)
    private String address;

    @Column(nullable = true)
    private String gstNumber;

    @Column(nullable = true)
    private String paymentTerms = "Cash"; // Cash, 15 Days, 30 Days, 45 Days

    @Column(nullable = true, length = 1000)
    private String notes;
}