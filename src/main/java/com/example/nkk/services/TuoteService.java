package com.example.nkk.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.example.nkk.models.Tuote;
import com.example.nkk.repositories.TuoteRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class TuoteService {

    @Autowired
    private TuoteRepo tuoteRepo;

    public List<Tuote> listaaTuotteet() {
        // Pageable pageable = PageRequest.of(sivunum - 1, 3);

        // if (hakuTermi.toLowerCase() != null) {
        // tuoteRepo.findAll(hakuTermi, pageable);
        // }
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

    // public Optional<Tuote> getTuoteById(long id) {
    // return tuoteRepo.findById(id);
    // }

    public void poistaTuote(long id) {
        tuoteRepo.deleteById(id);
    }

    // public Page<Tuote> listaaHalututTuotteet(String searchTerm, List<Long>
    // osastonumerot, Integer sivunumero) {

    // Integer tuotteidenMaaraSivulla = 2;
    // Pageable pageable = PageRequest.of(sivunumero, tuotteidenMaaraSivulla);

    // if (searchTerm.toLowerCase() != null) {

    // return tuoteRepo.haeTuotteet(searchTerm, osastonumerot, pageable);
    // } else {
    // return tuoteRepo.findByOsasto_IdIn(osastonumerot, pageable);
    // }

    // }
    public Page<Tuote> getTuotteetPageable(int nykyinenSivu, int maara, List<Long> osastonumerot, String searchTerm) {
        Pageable pageable = PageRequest.of(nykyinenSivu, maara);
        return tuoteRepo.haeTuotteet(searchTerm.toLowerCase(), osastonumerot, pageable);
    }

    // public List<Tuote> getTuotteetHakusanalla(String hakusana, List<Long>
    // osastot) {
    // if (hakusana.toLowerCase() != null) {
    // return tuoteRepo.haeTuotteet(hakusana, osastot);
    // }
    // return tuoteRepo.findByOsasto_IdIn(osastot);
    // }

    public Page<Tuote> getTuotteetOsastonMukaan(int nykyinenSivu, int maara, List<Long> osastonumerot) {
        Pageable pageable = PageRequest.of(nykyinenSivu, maara);
        return tuoteRepo.findByOsasto_IdIn(osastonumerot, pageable);
    }

    public void muokkaaTuote(Optional<Tuote> tuote) {
    }

}
