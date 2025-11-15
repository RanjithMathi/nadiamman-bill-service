package com.billflow.service;

import com.billflow.model.BatterySerial;
import com.billflow.repository.BatterySerialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WarrantyService {

    private final BatterySerialRepository batterySerialRepository;

    public Optional<BatterySerial> getWarrantyBySerialNumber(String serialNumber) {
        return batterySerialRepository.findBySerialNumber(serialNumber);
    }

    public List<BatterySerial> getAllWarranties() {
        return batterySerialRepository.findAll();
    }

    public List<BatterySerial> getActiveWarranties() {
        return batterySerialRepository.findAll().stream()
            .filter(BatterySerial::isWarrantyActive)
            .toList();
    }

    public List<BatterySerial> getExpiredWarranties() {
        return batterySerialRepository.findAll().stream()
            .filter(bs -> !bs.isWarrantyActive() && "active".equals(bs.getWarrantyStatus()))
            .toList();
    }

    public BatterySerial updateWarrantyStatus(String serialNumber, String status) {
        BatterySerial batterySerial = batterySerialRepository.findBySerialNumber(serialNumber)
            .orElseThrow(() -> new RuntimeException("Battery serial not found: " + serialNumber));

        batterySerial.setWarrantyStatus(status);
        return batterySerialRepository.save(batterySerial);
    }
}