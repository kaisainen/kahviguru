package com.example.nkk.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import com.example.nkk.models.Osasto;
import com.example.nkk.models.Toimittaja;
import com.example.nkk.models.Tuote;
import com.example.nkk.models.Valmistaja;
import com.example.nkk.repositories.OsastoRepo;
import com.example.nkk.repositories.ToimittajaRepo;
import com.example.nkk.repositories.TuoteRepo;
import com.example.nkk.repositories.ValmistajaRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TuoteService {

    @Autowired
    private TuoteRepo tuoteRepo;

    @Autowired
    private ValmistajaRepo valmistajaRepo;

    @Autowired
    private ToimittajaRepo toimittajaRepo;

    @Autowired
    private OsastoRepo osastoRepo;

    public List<Tuote> listaaTuotteet() {
        return tuoteRepo.findAll();
    }

    public void lisaaTuote(String nimi, String kuvaus, BigDecimal hinta, MultipartFile kuva, Long osastoID,
            Long valmistajaID, Long toimittajaID) throws IOException {
        Tuote tuote = new Tuote();
        tuote.setNimi(nimi);
        tuote.setKuvaus(kuvaus);
        tuote.setHinta(hinta);
        tuote.setKuva(kuva.getBytes());
        Osasto osasto = osastoRepo.getById(osastoID);
        tuote.setOsasto(osasto);
        Valmistaja valmistaja = valmistajaRepo.getById(valmistajaID);
        tuote.setValmistaja(valmistaja);
        Toimittaja toimittaja = toimittajaRepo.getById(toimittajaID);
        tuote.setToimittaja(toimittaja);

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

    // public List<Tuote> listaaHalututTuotteet(List<Long> halututTuotteetId) {
    // return tuoteRepo.findByOsasto_idIn(halututTuotteetId);
    // }

    public Page<Tuote> listaaHalututTuotteet(List<Long> halututTuotteetId, Pageable pageable) {
        return tuoteRepo.findByOsasto_IdIn(halututTuotteetId, pageable);
    }

}
