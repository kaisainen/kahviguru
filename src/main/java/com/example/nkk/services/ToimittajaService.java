package com.example.nkk.services;

import java.util.List;

import com.example.nkk.models.Toimittaja;
import com.example.nkk.repositories.ToimittajaRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToimittajaService {

    @Autowired
    private ToimittajaRepo toimittajaRepo;

    public List<Toimittaja> listaaToimittajat() {
        return toimittajaRepo.findAll();
    }

    public void lisaaToimittaja(Toimittaja toimittaja) {
        toimittajaRepo.save(toimittaja);
    }

    public Toimittaja muokkaaToimittaja(Toimittaja toimittaja) {
        return toimittajaRepo.save(toimittaja);
    }

    public Toimittaja getToimittajaById(long id) {
        return toimittajaRepo.getById(id);
    }

    public void poistaToimittaja(long id) {
        toimittajaRepo.deleteById(id);
    }

}
