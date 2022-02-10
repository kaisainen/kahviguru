package com.example.nkk.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.nkk.models.Tuote;
import com.example.nkk.services.OsastoService;
import com.example.nkk.services.ToimittajaService;
import com.example.nkk.services.TuoteService;
import com.example.nkk.services.ValmistajaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @RequestMapping("/kahvilaitteet")
    public String naytaEkaSivu(Model model) {
        return naytaKahvilaitteet(model, 1);
    }

    @GetMapping("/kahvilaitteet/{sivunum}")
    public String naytaKahvilaitteet(Model model, @PathVariable(name = "sivunum") Integer sivunum) {
        List<Tuote> tuotteet = new ArrayList<Tuote>();
        List<Long> kahvilaitteet = Arrays.asList(3L, 4L, 5L);
        Pageable pageable = PageRequest.of(sivunum - 1, 6);
        Page<Tuote> tuotesivut = tuoteService.listaaHalututTuotteet(kahvilaitteet, pageable);
        tuotteet = tuotesivut.getContent();
        Integer totalSivut = tuotesivut.getTotalPages();
        if (sivunum >= 1 && sivunum < totalSivut) {
            model.addAttribute("seuraava", sivunum + 1);
        }

        if (sivunum > 1 && sivunum <= totalSivut) {
            model.addAttribute("edellinen", sivunum - 1);
        }

        if (sivunum >= 1 && sivunum <= totalSivut) {
            model.addAttribute("nykyinen", sivunum);
        }
        model.addAttribute("totalPages", totalSivut);
        model.addAttribute("totalItems", tuotesivut.getTotalElements());
        model.addAttribute("kahvilaitteet", tuotteet);
        return "kahvilaitteet";
    }

    @GetMapping(path = "/kahvilaitteet/{id}/content", produces = "image/jpg")
    @ResponseBody
    public byte[] getKuvat(@PathVariable Long id) {
        return tuoteService.getTuoteById(id).getKuva();
    }

    @GetMapping("/kahvilaitteet/tuotetiedot/{id}")
    public String naytaKahvilaite(@PathVariable("id") long id, Model model) {
        Tuote tuote = tuoteService.getTuoteById(id);
        model.addAttribute("tuote", tuote);
        model.addAttribute("valmistajat", valmistajaService.listaaValmistajat());
        model.addAttribute("osastot", osastoService.listaaOsastot());
        model.addAttribute("toimittajat", toimittajaService.listaaToimittajat());
        return "tuotetiedot";
    }

    // KULUTUSTUOTTEET

    @RequestMapping("/kulutustuotteet")
    public String naytaKulutusEkaSivu(Model model) {
        return naytaKulutustuotteet(model, 1);
    }

    @GetMapping("/kulutustuotteet/{sivunum}")
    public String naytaKulutustuotteet(Model model, @PathVariable(name = "sivunum") Integer sivunum) {
        List<Tuote> tuotteet = new ArrayList<Tuote>();
        List<Long> kulutustuotteet = Arrays.asList(6L, 7L, 8L, 9L);
        Pageable pageable = PageRequest.of(sivunum - 1, 12);
        Page<Tuote> tuotesivut = tuoteService.listaaHalututTuotteet(kulutustuotteet, pageable);
        tuotteet = tuotesivut.getContent();
        Integer totalSivut = tuotesivut.getTotalPages();
        if (sivunum >= 1 && sivunum < totalSivut) {
            model.addAttribute("seuraava", sivunum + 1);
        }

        if (sivunum > 1 && sivunum <= totalSivut) {
            model.addAttribute("edellinen", sivunum - 1);
        }

        if (sivunum >= 1 && sivunum <= totalSivut) {
            model.addAttribute("nykyinen", sivunum);
        }
        model.addAttribute("totalPages", totalSivut);
        model.addAttribute("totalItems", tuotesivut.getTotalElements());
        model.addAttribute("kulutustuotteet", tuotteet);
        return "kulutustuotteet";
    }

    @GetMapping(path = "/kulutustuotteet/{id}/content", produces = "image/jpg")
    @ResponseBody
    public byte[] getKuvatKulutustuotteet(@PathVariable Long id) {
        return tuoteService.getTuoteById(id).getKuva();
    }

    @GetMapping("/kulutustuotteet/tuotetiedot/{id}")
    public String naytaKulutustuote(@PathVariable("id") long id, Model model) {
        Tuote tuote = tuoteService.getTuoteById(id);
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
    }

}
