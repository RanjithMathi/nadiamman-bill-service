package com.billflow.repository;

import com.billflow.model.BatterySerial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BatterySerialRepository extends JpaRepository<BatterySerial, Long> {

    Optional<BatterySerial> findBySerialNumber(String serialNumber);

    List<BatterySerial> findByProductId(Long productId);

    @Query("SELECT bs FROM BatterySerial bs WHERE bs.invoiceItem.invoice.id = :invoiceId")
    List<BatterySerial> findByInvoiceId(@Param("invoiceId") Long invoiceId);

    @Query("SELECT bs FROM BatterySerial bs WHERE bs.serialNumber IN :serialNumbers")
    List<BatterySerial> findBySerialNumbers(@Param("serialNumbers") List<String> serialNumbers);
}