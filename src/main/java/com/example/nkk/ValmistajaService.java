package com.example.nkk;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValmistajaService {

    @Autowired
    private ValmistajaRepo valmistajaRepo;

    public List<Valmistaja> listaaValmistajat() {
        return valmistajaRepo.findAll();
    }

    public void uusiValmistaja(Valmistaja valmistaja) {
        valmistajaRepo.save(valmistaja);
    }

    public Valmistaja muokkaaValmistajaa(Valmistaja valmistaja) {
        return valmistajaRepo.save(valmistaja);
    }

    public Valmistaja getValmistajaById(long id) {
        return valmistajaRepo.getById(id);
    }

    public void poistaValmistaja(long id) {
        valmistajaRepo.deleteById(id);
    }

}
