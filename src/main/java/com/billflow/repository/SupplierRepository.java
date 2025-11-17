package com.billflow.repository;

import com.billflow.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    Optional<Supplier> findByName(String name);

    List<Supplier> findByNameContainingIgnoreCase(String name);

    @Query("SELECT s FROM Supplier s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "s.phone LIKE CONCAT('%', :search, '%') OR " +
           "LOWER(s.email) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<Supplier> searchSuppliers(@Param("search") String search);
}