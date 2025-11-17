package com.billflow.service;

import com.billflow.model.Product;
import com.billflow.model.Supplier;
import com.billflow.repository.ProductRepository;
import com.billflow.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));
    }

    public List<Supplier> searchSuppliers(String search) {
        if (search == null || search.trim().isEmpty()) {
            return getAllSuppliers();
        }
        return supplierRepository.searchSuppliers(search.trim());
    }

    @Transactional
    public Supplier createSupplier(Supplier supplier) {
        // Check if supplier with same name already exists
        if (supplierRepository.findByName(supplier.getName()).isPresent()) {
            throw new RuntimeException("Supplier with name '" + supplier.getName() + "' already exists");
        }
        return supplierRepository.save(supplier);
    }

    @Transactional
    public Supplier updateSupplier(Long id, Supplier supplierDetails) {
        Supplier supplier = getSupplierById(id);

        // Check if name is being changed and if it conflicts
        if (!supplier.getName().equals(supplierDetails.getName()) &&
            supplierRepository.findByName(supplierDetails.getName()).isPresent()) {
            throw new RuntimeException("Supplier with name '" + supplierDetails.getName() + "' already exists");
        }

        supplier.setName(supplierDetails.getName());
        supplier.setPhone(supplierDetails.getPhone());
        supplier.setEmail(supplierDetails.getEmail());
        supplier.setAddress(supplierDetails.getAddress());
        supplier.setGstNumber(supplierDetails.getGstNumber());
        supplier.setPaymentTerms(supplierDetails.getPaymentTerms());
        supplier.setNotes(supplierDetails.getNotes());

        return supplierRepository.save(supplier);
    }

    @Transactional
    public void deleteSupplier(Long id) {
        Supplier supplier = getSupplierById(id);
        // TODO: Add check for linked products before deletion
        supplierRepository.delete(supplier);
    }

    public boolean canDeleteSupplier(Long id) {
        // Check if any products are linked to this supplier
        List<Product> linkedProducts = productRepository.findBySupplierId(id);
        return linkedProducts.isEmpty();
    }
}