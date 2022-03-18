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

    public Page<Tuote> listaaTuotteet(Integer sivunum) {
        Pageable pageable = PageRequest.of(sivunum - 1, 3);

        // if (hakuTermi.toLowerCase() != null) {
        // tuoteRepo.findAll(hakuTermi, pageable);
        // }
        return tuoteRepo.findAll(pageable);
    }

    public void lisaaTuote(Tuote tuote) {
        tuoteRepo.save(tuote);
    }

    public Tuote muokkaaTuote(Tuote tuote) {
        return tuoteRepo.save(tuote);
    }

    public Optional<Tuote> getTuoteById(long id) {
        return tuoteRepo.findById(id);
    }

    public void poistaTuote(long id) {
        tuoteRepo.deleteById(id);
    }

    public Page<Tuote> listaaHalututTuotteet(String searchTerm, List<Long> osastonumerot, Integer sivunumero) {

        Integer tuotteidenMaaraSivulla = 3;
        Pageable pageable = PageRequest.of(sivunumero - 1, tuotteidenMaaraSivulla);

        if (searchTerm.toLowerCase() != null) {

            return tuoteRepo.haeTuotteet(searchTerm, osastonumerot, pageable);
        } else {
            return tuoteRepo.findByOsasto_IdIn(osastonumerot, pageable);
        }

    }

    public void muokkaaTuote(Optional<Tuote> tuote) {
    }

}
