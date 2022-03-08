package com.example.nkk.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

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

    @GetMapping
    public String naytaAdmin(Model model) {
        return "admin";
    }
    // TUOTTEIDEN HALLINTA

    // @GetMapping("/admin/tuotteet")
    @GetMapping("/tuotteet")
    public String naytaTuotteet(Model model) {
        model.addAttribute("tuotteet", tuoteService.listaaTuotteet());
        model.addAttribute("valmistajat", valmistajaService.listaaValmistajat());
        model.addAttribute("osastot", osastoService.listaaOsastot());
        model.addAttribute("toimittajat", toimittajaService.listaaToimittajat());
        return "tuotteet";
    }

    @PostMapping("/admin/tuotteet/lisaa")
    public String lisaaTuote(@RequestParam String nimi, @RequestParam BigDecimal hinta,
            @RequestParam String kuvaus, @RequestParam Long osastoID,
            @RequestParam Long toimittajaID,
            @RequestParam Long valmistajaID, @RequestParam("kuva") MultipartFile kuva)
            throws IOException {
        tuoteService.lisaaTuote(nimi, kuvaus, hinta, kuva, osastoID, valmistajaID, toimittajaID);
        return "redirect:/admin/tuotteet";
    }

    @GetMapping(path = "/admin/tuotteet/edittuote/{id}/content", produces = "image/jpg")
    @ResponseBody
    public byte[] get(@PathVariable Long id) {
        return tuoteService.getTuoteById(id).getKuva();
    }

    @GetMapping("/admin/tuotteet/edittuote/{id}")
    public String naytaMuokkaaTuote(@PathVariable("id") long id, Model model) {
        Tuote tuote = tuoteService.getTuoteById(id);
        model.addAttribute("tuote", tuote);
        model.addAttribute("valmistajat", valmistajaService.listaaValmistajat());
        model.addAttribute("osastot", osastoService.listaaOsastot());
        model.addAttribute("toimittajat", toimittajaService.listaaToimittajat());
        return "edittuote";
    }

    @PostMapping("/admin/tuotteet/edittuote/{id}")
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
        return "redirect:/admin/tuotteet/edittuote/" + id;
    }

    @RequestMapping(value = "admin/tuotteet/poista", method = RequestMethod.GET)
    public String poistaTuote(@RequestParam(name = "tuoteId") Long id) {
        tuoteService.poistaTuote(id);
        return "redirect:/admin/tuotteet";
    }

    // VALMISTAJIEN HALLINTA

    @GetMapping("/admin/valmistajat")
    public String naytaValmistajat(Model model) {
        model.addAttribute("valmistajat", valmistajaService.listaaValmistajat());
        return "valmistajat";
    }

    @PostMapping("/admin/valmistajat/lisaa")
    public String lisaaValmistaja(Model model, @ModelAttribute Valmistaja valmistaja) {
        valmistajaService.uusiValmistaja(valmistaja);
        return "redirect:/admin/valmistajat";
    }

    @GetMapping("/admin/valmistajat/muokkaa/{id}")
    public String naytaMuokkaaValmistaja(@PathVariable("id") long id, Model model) {
        Valmistaja valmistaja = valmistajaService.getValmistajaById(id);
        model.addAttribute("valmistaja", valmistaja);
        return "muokkaa";
    }

    @PostMapping("/admin/valmistajat/muokkaa/{id}")
    public String muokkaaValmistajaa(@PathVariable("id") Long id, @ModelAttribute Valmistaja valmistaja) {
        Valmistaja muokkaaValmistaja = valmistajaService.getValmistajaById(id);
        muokkaaValmistaja.setNimi(valmistaja.getNimi());
        muokkaaValmistaja.setUrl(valmistaja.getUrl());
        valmistajaService.muokkaaValmistajaa(muokkaaValmistaja);
        return "redirect:/admin/valmistajat/muokkaa/" + id;
    }

    @RequestMapping(value = "admin/valmistajat/poista", method = RequestMethod.GET)
    public String poistaValmistaja(@RequestParam(name = "valmistajaId") Long id) {
        valmistajaService.poistaValmistaja(id);
        return "redirect:/admin/valmistajat";
    }

    // OSASTOJEN HALLINTA

    // @PostConstruct
    // public void init() {
    // osastoService.lisaaOsasto(new Osasto("Kahvilaitteet", 0L, new
    // ArrayList<>()));
    // osastoService.lisaaOsasto(new Osasto("Kulutustuotteet", 0L, new
    // ArrayList<>()));
    // osastoService.lisaaOsasto(new Osasto("Espressolaitteet", 1L, new
    // ArrayList<>()));
    // osastoService.lisaaOsasto(new Osasto("Kahvinkeittimet", 1L, new
    // ArrayList<>()));
    // osastoService.lisaaOsasto(new Osasto("Kahvimyllyt", 1L, new ArrayList<>()));
    // osastoService.lisaaOsasto(new Osasto("Suodattimet", 2L, new ArrayList<>()));
    // osastoService.lisaaOsasto(new Osasto("Kahvilaadut", 2L, new ArrayList<>()));
    // osastoService.lisaaOsasto(new Osasto("Espressot", 7L, new ArrayList<>()));
    // osastoService.lisaaOsasto(new Osasto("Suodatinkahvit", 7L, new
    // ArrayList<>()));
    // toimittajaService.lisaaToimittaja(new Toimittaja("GG", "kalle",
    // "kalle@gg.com", new ArrayList<>()));
    // valmistajaService.uusiValmistaja(new Valmistaja("Philips", "www.philips.com",
    // new ArrayList<>()));

    // }

    @GetMapping("/admin/osastot")
    public String naytaOsastot(Model model) {
        model.addAttribute("osastot", osastoService.listaaOsastot());
        return "osastot";
    }

    @PostMapping("/admin/osastot/lisaa")
    public String lisaaOsasto(Model model, @ModelAttribute Osasto osasto) {
        osastoService.lisaaOsasto(osasto);
        return "redirect:/admin/osastot";
    }

    @GetMapping("/admin/osastot/editosasto/{id}")
    public String naytaMuokkaaOsasto(@PathVariable("id") long id, Model model) {
        Osasto osasto = osastoService.getOsastoById(id);
        model.addAttribute("osasto", osasto);
        return "editosasto";
    }

    @PostMapping("/admin/osastot/editosasto/{id}")
    public String muokkaaOsastoa(@PathVariable("id") Long id, @ModelAttribute Osasto osasto) {
        Osasto muokkaaOsastoa = osastoService.getOsastoById(id);
        muokkaaOsastoa.setNimi(osasto.getNimi());
        muokkaaOsastoa.setOsastoIDP(osasto.getOsastoIDP());
        osastoService.muokkaaOsastoa(muokkaaOsastoa);
        return "redirect:/admin/osastot/editosasto/" + id;
    }

    @RequestMapping(value = "admin/osastot/poista", method = RequestMethod.GET)
    public String poistaOsasto(@RequestParam(name = "osastoId") Long id) {
        osastoService.poistaOsasto(id);
        return "redirect:/admin/osastot";
    }

    // HALLINNOI TOIMITTAJIA

    @GetMapping("/admin/toimittajat")
    public String naytaToimittajat(Model model) {
        model.addAttribute("toimittajat", toimittajaService.listaaToimittajat());
        return "toimittajat";
    }

    @PostMapping("/admin/toimittajat/lisaa")
    public String lisaaToimittaja(Model model, @ModelAttribute Toimittaja toimittaja) {
        toimittajaService.lisaaToimittaja(toimittaja);
        return "redirect:/admin/toimittajat";
    }

    @GetMapping("/admin/toimittajat/edit/{id}")
    public String naytaMuokkaaToimittaja(@PathVariable("id") long id, Model model) {
        Toimittaja toimittaja = toimittajaService.getToimittajaById(id);
        model.addAttribute("toimittaja", toimittaja);
        return "edit";
    }

    @PostMapping("/admin/toimittajat/edit/{id}")
    public String muokkaaToimittajaa(@PathVariable("id") Long id, @ModelAttribute Toimittaja toimittaja) {
        Toimittaja muokkaaToimittaja = toimittajaService.getToimittajaById(id);
        muokkaaToimittaja.setNimi(toimittaja.getNimi());
        muokkaaToimittaja.setYhteyshenkilo(toimittaja.getYhteyshenkilo());
        muokkaaToimittaja.setYhteyshenkiloemail(toimittaja.getYhteyshenkiloemail());
        toimittajaService.muokkaaToimittaja(muokkaaToimittaja);
        return "redirect:/admin/toimittajat/edit/" + id;
    }

    @RequestMapping(value = "admin/toimittajat/poista", method = RequestMethod.GET)
    public String poistaToimittaja(@RequestParam(name = "toimittajaId") Long id) {
        toimittajaService.poistaToimittaja(id);
        return "redirect:/admin/toimittajat";
    }

}
