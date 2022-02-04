package com.example.nkk;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TuoteService {

    @Autowired
    private TuoteRepo tuoteRepo;

    public List<Tuote> listaaTuotteet() {
        return tuoteRepo.findAll();
    }

    public void lisaaTuote(Tuote tuote) {
        tuoteRepo.save(tuote);
    }

    public Tuote muokkaaTuote(Tuote tuote) {
        return tuoteRepo.save(tuote);
    }

    public Tuote getTuoteById(long id) {
        return tuoteRepo.getById(id);
    }

    public void poistaTuote(long id) {
        tuoteRepo.deleteById(id);
    }

}
