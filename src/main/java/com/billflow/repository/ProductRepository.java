package com.billflow.repository;

import com.billflow.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);

    @Query("SELECT p FROM Product p WHERE :serialNumber IN elements(p.serialNumbers)")
    Optional<Product> findBySerialNumber(@Param("serialNumber") String serialNumber);

    List<Product> findByIsBattery(Boolean isBattery);
    List<Product> findBySupplierId(Long supplierId);
}