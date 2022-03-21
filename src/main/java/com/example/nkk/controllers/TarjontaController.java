package com.example.nkk.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.nkk.models.Tuote;
import com.example.nkk.services.OsastoService;
import com.example.nkk.services.ToimittajaService;
import com.example.nkk.services.TuoteService;
import com.example.nkk.services.ValmistajaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TarjontaController {

    @Autowired
    private OsastoService osastoService;

    @Autowired
    private TuoteService tuoteService;

    @Autowired
    private ValmistajaService valmistajaService;

    @Autowired
    private ToimittajaService toimittajaService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    // KAHVILAITTEET
    @RequestMapping(path = "/kahvilaitteet")
    public String naytaEkaSivu(Model model, String hakusana) {
        return naytaKahvilaitteet(model, 1, hakusana);
    }

    @GetMapping("/kahvilaitteet/{sivunum}")
    public String naytaKahvilaitteet(Model model, @PathVariable(name = "sivunum") Integer sivunum,
            @Param("searchTerm") String searchTerm) {

        List<Long> kahvilaitteet = Arrays.asList(3L, 4L, 5L);

        if (searchTerm != null) {
            Page<Tuote> tuotesivut = tuoteService.getTuotteetPageable(sivunum - 1, 2, kahvilaitteet, searchTerm);
            List<Tuote> tuotteet = tuotesivut.getContent();
            Integer totalSivut = tuotesivut.getTotalPages();
            Long totalTuotteet = tuotesivut.getTotalElements();
            model.addAttribute("nykyinenSivu", sivunum);
            model.addAttribute("totalPages", totalSivut);
            model.addAttribute("totalItems", totalTuotteet);
            model.addAttribute("kahvilaitteet", tuotteet);
            return "kahvilaitteet";

        } else {
            Page<Tuote> tuotesivut = tuoteService.getTuotteetOsastonMukaan(sivunum - 1, 2, kahvilaitteet);
            List<Tuote> tuotteet = tuotesivut.getContent();
            Integer totalSivut = tuotesivut.getTotalPages();
            Long totalTuotteet = tuotesivut.getTotalElements();
            model.addAttribute("nykyinenSivu", sivunum);
            model.addAttribute("totalPages", totalSivut);
            model.addAttribute("totalItems", totalTuotteet);
            model.addAttribute("kahvilaitteet", tuotteet);
            return "kahvilaitteet";
        }
    }

    @GetMapping(path = "/kahvilaitteet/{id}/content", produces = "image/jpg")
    @ResponseBody
    public byte[] getKuvat(@PathVariable Long id) {
        return tuoteService.getTuoteById(id).getKuva();
        // return tuoteService.getTuoteById(id).get().getKuva();
    }

    @GetMapping("/kahvilaitteet/tuotetiedot/{id}")
    public String naytaKahvilaite(@PathVariable("id") long id, Model model) {
        Tuote tuote = tuoteService.getTuoteById(id);
        // Optional<Tuote> tuote = tuoteService.getTuoteById(id);
        model.addAttribute("tuote", tuote);
        model.addAttribute("valmistajat", valmistajaService.listaaValmistajat());
        model.addAttribute("osastot", osastoService.listaaOsastot());
        model.addAttribute("toimittajat", toimittajaService.listaaToimittajat());
        return "tuotetiedot";
    }

    // KULUTUSTUOTTEET

    @RequestMapping("/kulutustuotteet")
    public String naytaKulutusEkaSivu(Model model, String searchTerm) {
        return naytaKulutustuotteet(model, 1, searchTerm);
    }

    @GetMapping("/kulutustuotteet/{sivunum}")
    public String naytaKulutustuotteet(Model model, @PathVariable(name = "sivunum") Integer sivunum,
            @Param("searchTerm") String searchTerm) {

        List<Long> kulutustuotteet = Arrays.asList(6L, 7L, 8L, 9L);

        if (searchTerm != null) {
            Page<Tuote> tuotesivut = tuoteService.getTuotteetPageable(sivunum - 1, 2, kulutustuotteet, searchTerm);
            List<Tuote> tuotteet = tuotesivut.getContent();
            Integer totalSivut = tuotesivut.getTotalPages();
            Long totalTuotteet = tuotesivut.getTotalElements();
            model.addAttribute("nykyinenSivu", sivunum);
            model.addAttribute("totalPages", totalSivut);
            model.addAttribute("totalItems", totalTuotteet);
            model.addAttribute("kulutustuotteet", tuotteet);
            return "kulutustuotteet";

        } else {
            Page<Tuote> tuotesivut = tuoteService.getTuotteetOsastonMukaan(sivunum - 1, 2, kulutustuotteet);
            List<Tuote> tuotteet = tuotesivut.getContent();
            Integer totalSivut = tuotesivut.getTotalPages();
            Long totalTuotteet = tuotesivut.getTotalElements();
            model.addAttribute("nykyinenSivu", sivunum);
            model.addAttribute("totalPages", totalSivut);
            model.addAttribute("totalItems", totalTuotteet);
            model.addAttribute("kulutustuotteet", tuotteet);
            return "kulutustuotteet";
        }
    }

    @GetMapping(path = "/kulutustuotteet/{id}/content", produces = "image/jpg")
    @ResponseBody
    public byte[] getKuvatKulutustuotteet(@PathVariable Long id) {
        // return tuoteService.getTuoteById(id).get().getKuva();
        return tuoteService.getTuoteById(id).getKuva();
    }

    @GetMapping("/kulutustuotteet/tuotetiedot/{id}")
    public String naytaKulutustuote(@PathVariable("id") long id, Model model) {
        Tuote tuote = tuoteService.getTuoteById(id);
        // Optional<Tuote> tuote = tuoteService.getTuoteById(id);
        model.addAttribute("tuote", tuote);
        model.addAttribute("valmistajat", valmistajaService.listaaValmistajat());
        model.addAttribute("osastot", osastoService.listaaOsastot());
        model.addAttribute("toimittajat", toimittajaService.listaaToimittajat());
        return "tuotetiedot";
    }

    @GetMapping(path = "/tuotetiedot/{id}/content", produces = "image/jpg")
    @ResponseBody
    public byte[] getKuvaKulutustuote(@PathVariable Long id) {
        return tuoteService.getTuoteById(id).getKuva();
        // return tuoteService.getTuoteById(id).get().getKuva();
    }

}
