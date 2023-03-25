package com.example.nkk.services;

import java.util.List;

import com.example.nkk.models.Supplier;
import com.example.nkk.repositories.SupplierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> listSuppliers() {
        return supplierRepository.findAll();
    }

    public void addSupplier(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    public Supplier editSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Supplier getSupplierById(long id) {
        return supplierRepository.getById(id);
    }

    public void deleteSupplier(long id) {
        supplierRepository.deleteById(id);
    }

}
