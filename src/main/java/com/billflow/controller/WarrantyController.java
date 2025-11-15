package com.billflow.controller;

import com.billflow.model.BatterySerial;
import com.billflow.service.WarrantyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warranty")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class WarrantyController {

    private final WarrantyService warrantyService;

    @GetMapping
    public ResponseEntity<List<BatterySerial>> getAllWarranties() {
        return ResponseEntity.ok(warrantyService.getAllWarranties());
    }

    @GetMapping("/active")
    public ResponseEntity<List<BatterySerial>> getActiveWarranties() {
        return ResponseEntity.ok(warrantyService.getActiveWarranties());
    }

    @GetMapping("/expired")
    public ResponseEntity<List<BatterySerial>> getExpiredWarranties() {
        return ResponseEntity.ok(warrantyService.getExpiredWarranties());
    }

    @GetMapping("/serial/{serialNumber}")
    public ResponseEntity<BatterySerial> getWarrantyBySerialNumber(@PathVariable String serialNumber) {
        return warrantyService.getWarrantyBySerialNumber(serialNumber)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/serial/{serialNumber}/status")
    public ResponseEntity<BatterySerial> updateWarrantyStatus(
            @PathVariable String serialNumber,
            @RequestParam String status) {
        BatterySerial updated = warrantyService.updateWarrantyStatus(serialNumber, status);
        return ResponseEntity.ok(updated);
    }
}