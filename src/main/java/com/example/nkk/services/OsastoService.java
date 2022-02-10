package com.example.nkk.services;

import java.util.List;

import com.example.nkk.models.Osasto;
import com.example.nkk.repositories.OsastoRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OsastoService {

    @Autowired
    private OsastoRepo osastoRepo;

    public List<Osasto> listaaOsastot() {
        return osastoRepo.findAll();
    }

    public Osasto getOsastoById(long id) {
        return osastoRepo.getById(id);
    }

}
