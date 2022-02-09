package com.example.nkk.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.example.nkk.models.Osasto;
import com.example.nkk.models.Toimittaja;
import com.example.nkk.models.Tuote;
import com.example.nkk.models.Valmistaja;
import com.example.nkk.services.OsastoService;
import com.example.nkk.services.ToimittajaService;
import com.example.nkk.services.TuoteService;
import com.example.nkk.services.ValmistajaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AdminController {

    @Autowired
    private OsastoService osastoService;

    @Autowired
    private TuoteService tuoteService;

    @Autowired
    private ValmistajaService valmistajaService;

    @Autowired
    private ToimittajaService toimittajaService;

    // TUOTTEIDEN HALLINTA

    @GetMapping("/tuotteet")
    public String naytaTuotteet(Model model) {
        model.addAttribute("tuotteet", tuoteService.listaaTuotteet());
        model.addAttribute("valmistajat", valmistajaService.listaaValmistajat());
        model.addAttribute("osastot", osastoService.listaaOsastot());
        model.addAttribute("toimittajat", toimittajaService.listaaToimittajat());
        return "tuotteet";
    }

    @PostMapping("/tuotteet")
    public String lisaaTuote(@RequestParam String nimi, @RequestParam BigDecimal hinta,
            @RequestParam String kuvaus, @RequestParam Long osastoID,
            @RequestParam Long toimittajaID,
            @RequestParam Long valmistajaID, @RequestParam("kuva") MultipartFile kuva)
            throws IOException {
        Tuote uusiTuote = new Tuote();
        uusiTuote.setNimi(nimi);
        uusiTuote.setHinta(hinta);
        uusiTuote.setKuva(kuva.getBytes());
        ;
        uusiTuote.setKuvaus(kuvaus);

        Osasto osasto = osastoService.getOsastoById(osastoID);
        uusiTuote.setOsasto(osasto);
        Valmistaja valmistaja = valmistajaService.getValmistajaById(valmistajaID);
        uusiTuote.setValmistaja(valmistaja);
        Toimittaja toimittaja = toimittajaService.getToimittajaById(toimittajaID);
        uusiTuote.setToimittaja(toimittaja);
        tuoteService.lisaaTuote(uusiTuote);

        return "redirect:/tuotteet";
    }

    @GetMapping(path = "/tuotteet/edittuote/{id}/content", produces = "image/jpg")
    @ResponseBody
    public byte[] get(@PathVariable Long id) {
        return tuoteService.getTuoteById(id).getKuva();
    }

    @GetMapping("/tuotteet/edittuote/{id}")
    public String naytaMuokkaaTuote(@PathVariable("id") long id, Model model) {
        Tuote tuote = tuoteService.getTuoteById(id);
        model.addAttribute("tuote", tuote);
        model.addAttribute("valmistajat", valmistajaService.listaaValmistajat());
        model.addAttribute("osastot", osastoService.listaaOsastot());
        model.addAttribute("toimittajat", toimittajaService.listaaToimittajat());
        return "edittuote";
    }

    @PostMapping("/tuotteet/edittuote/{id}")
    public String muokkaaTuote(@PathVariable("id") Long id, @RequestParam String nimi,
            @RequestParam BigDecimal hinta,
            @RequestParam String kuvaus, @RequestParam Long osastoID,
            @RequestParam Long toimittajaID,
            @RequestParam Long valmistajaID) {
        Tuote tuote = tuoteService.getTuoteById(id);
        tuote.setNimi(nimi);
        tuote.setHinta(hinta);
        tuote.setKuvaus(kuvaus);
        Osasto osasto = osastoService.getOsastoById(osastoID);
        tuote.setOsasto(osasto);
        Valmistaja valmistaja = valmistajaService.getValmistajaById(valmistajaID);
        tuote.setValmistaja(valmistaja);
        Toimittaja toimittaja = toimittajaService.getToimittajaById(toimittajaID);
        tuote.setToimittaja(toimittaja);
        tuoteService.muokkaaTuote(tuote);
        return "redirect:/tuotteet/edittuote/" + id;
    }

    @RequestMapping(value = "tuotteet/poista", method = RequestMethod.GET)
    public String poistaTuote(@RequestParam(name = "tuoteId") Long id) {
        tuoteService.poistaTuote(id);
        return "redirect:/tuotteet";
    }

    // VALMISTAJIEN HALLINTA

    @GetMapping("/valmistajat")
    public String naytaValmistajat(Model model) {
        model.addAttribute("valmistajat", valmistajaService.listaaValmistajat());
        return "valmistajat";
    }

    @PostMapping("/valmistajat")
    public String lisaaValmistaja(Model model, @ModelAttribute Valmistaja valmistaja) {
        valmistajaService.uusiValmistaja(valmistaja);
        return "redirect:/valmistajat";
    }

    @GetMapping("/valmistajat/muokkaa/{id}")
    public String naytaMuokkaaValmistaja(@PathVariable("id") long id, Model model) {
        Valmistaja valmistaja = valmistajaService.getValmistajaById(id);
        model.addAttribute("valmistaja", valmistaja);
        return "muokkaa";
    }

    @PostMapping("/valmistajat/muokkaa/{id}")
    public String muokkaaValmistajaa(@PathVariable("id") Long id, @ModelAttribute Valmistaja valmistaja) {
        Valmistaja muokkaaValmistaja = valmistajaService.getValmistajaById(id);
        muokkaaValmistaja.setNimi(valmistaja.getNimi());
        muokkaaValmistaja.setUrl(valmistaja.getUrl());
        valmistajaService.muokkaaValmistajaa(muokkaaValmistaja);
        return "redirect:/valmistajat/muokkaa/" + id;
    }

    // TOIMITTAJIEN HALLINTA

    @GetMapping("/toimittajat")
    public String naytaToimittajat(Model model) {
        model.addAttribute("toimittajat", toimittajaService.listaaToimittajat());
        return "toimittajat";
    }

    @PostMapping("/toimittajat")
    public String lisaaToimittaja(Model model, @ModelAttribute Toimittaja toimittaja) {
        toimittajaService.lisaaToimittaja(toimittaja);
        return "redirect:/toimittajat";
    }

    @GetMapping("/toimittajat/edit/{id}")
    public String naytaMuokkaaToimittaja(@PathVariable("id") long id, Model model) {
        Toimittaja toimittaja = toimittajaService.getToimittajaById(id);
        model.addAttribute("toimittaja", toimittaja);
        return "edit";
    }

    @PostMapping("/toimittajat/edit/{id}")
    public String muokkaaToimittajaa(@PathVariable("id") Long id, @ModelAttribute Toimittaja toimittaja) {
        Toimittaja muokkaaToimittaja = toimittajaService.getToimittajaById(id);
        muokkaaToimittaja.setNimi(toimittaja.getNimi());
        muokkaaToimittaja.setYhteyshenkilo(toimittaja.getYhteyshenkilo());
        muokkaaToimittaja.setYhteyshenkiloemail(toimittaja.getYhteyshenkiloemail());
        toimittajaService.muokkaaToimittaja(muokkaaToimittaja);
        return "redirect:/toimittajat/edit/" + id;
    }

    @RequestMapping(value = "valmistajat/poista", method = RequestMethod.GET)
    public String poistaValmistaja(@RequestParam(name = "valmistajaId") Long id) {
        valmistajaService.poistaValmistaja(id);
        return "redirect:/valmistajat";
    }

    @RequestMapping(value = "toimittajat/poista", method = RequestMethod.GET)
    public String poistaToimittaja(@RequestParam(name = "toimittajaId") Long id) {
        toimittajaService.poistaToimittaja(id);
        return "redirect:/toimittajat";
    }

}
