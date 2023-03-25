package com.example.nkk.services;

import java.util.List;

import com.example.nkk.models.Manufacturer;
import com.example.nkk.repositories.ManufacturerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManufacturerService {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    public List<Manufacturer> listManufacturers() {
        return manufacturerRepository.findAll();
    }

    public void addManufacturer(Manufacturer manufacturer) {
        manufacturerRepository.save(manufacturer);
    }

    public Manufacturer editManufacturera(Manufacturer manufacturer) {
        manufacturerRepository.save(manufacturer);
        return manufacturer;
    }

    public Manufacturer getmanufacturerById(long id) {
        return manufacturerRepository.getById(id);
    }

    public void deleteManufacturer(long id) {
        manufacturerRepository.deleteById(id);
    }

}
