package com.example.nkk;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NkkController {

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

    @GetMapping("/tuotteet")
    public String naytaTuotteet(Model model) {
        model.addAttribute("tuotteet", tuoteService.listaaTuotteet());
        model.addAttribute("valmistajat", valmistajaService.listaaValmistajat());
        model.addAttribute("osastot", osastoService.listaaOsastot());
        model.addAttribute("toimittajat", toimittajaService.listaaToimittajat());
        return "tuotteet";
    }

    @PostMapping("/tuotteet")
    public String lisaaTuote(@RequestParam String nimi, @RequestParam String kuva, @RequestParam BigDecimal hinta,
            @RequestParam String kuvaus, @RequestParam Long osastoID,
            @RequestParam Long toimittajaID,
            @RequestParam Long valmistajaID) {

        Tuote uusiTuote = new Tuote();
        uusiTuote.setNimi(nimi);
        uusiTuote.setHinta(hinta);
        uusiTuote.setKuva(kuva);
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

    @GetMapping("/valmistajat")
    public String naytaValmistajat(Model model) {
        model.addAttribute("valmistajat", valmistajaService.listaaValmistajat());
        return "valmistajat";
    }

    @PostMapping("/valmistajat")
    public String lisaaValmistaja(@RequestParam String nimi, @RequestParam String url) {
        Valmistaja uusiValmistaja = new Valmistaja();
        uusiValmistaja.setNimi(nimi);
        uusiValmistaja.setUrl(url);
        valmistajaService.uusiValmistaja(uusiValmistaja);
        return "redirect:/valmistajat";
    }

    @GetMapping("/toimittajat")
    public String naytaToimittajat(Model model) {
        model.addAttribute("toimittajat", toimittajaService.listaaToimittajat());
        return "toimittajat";
    }

    @PostMapping("/toimittajat")
    public String lisaaToimittaja(@RequestParam String nimi, @RequestParam String yhteyshenkilo,
            @RequestParam String yhteyshenkiloemail) {
        Toimittaja uusToimittaja = new Toimittaja();
        uusToimittaja.setNimi(nimi);
        uusToimittaja.setYhteyshenkilo(yhteyshenkilo);
        uusToimittaja.setYhteyshenkiloemail(yhteyshenkiloemail);
        toimittajaService.lisaaToimittaja(uusToimittaja);
        return "redirect:/toimittajat";
    }

    @GetMapping("/valmistajat/muokkaa/{id}")
    public String naytaMuokkaaValmistaja(@PathVariable("id") long id, Model model) {
        Valmistaja valmistaja = valmistajaService.getValmistajaById(id);
        model.addAttribute("valmistaja", valmistaja);
        return "muokkaa";
    }

    @PostMapping("/valmistajat/muokkaa/{id}")
    public String muokkaaValmistajaa(@PathVariable("id") Long id, @RequestParam String nimi, @RequestParam String url) {
        Valmistaja valmistaja = valmistajaService.getValmistajaById(id);
        valmistaja.setNimi(nimi);
        valmistaja.setUrl(url);
        valmistajaService.muokkaaValmistajaa(valmistaja);
        return "redirect:/valmistajat/muokkaa/" + id;
    }

    @GetMapping("/toimittajat/edit/{id}")
    public String naytaMuokkaaToimittaja(@PathVariable("id") long id, Model model) {
        Toimittaja toimittaja = toimittajaService.getToimittajaById(id);
        model.addAttribute("toimittaja", toimittaja);
        return "edit";
    }

    @PostMapping("/toimittajat/edit/{id}")
    public String muokkaaToimittajaa(@PathVariable("id") Long id, @RequestParam String nimi,
            @RequestParam String yhteyshenkilo, @RequestParam String yhteyshenkiloemail) {
        Toimittaja toimittaja = toimittajaService.getToimittajaById(id);
        toimittaja.setNimi(nimi);
        toimittaja.setYhteyshenkilo(yhteyshenkilo);
        toimittaja.setYhteyshenkiloemail(yhteyshenkiloemail);
        toimittajaService.muokkaaToimittaja(toimittaja);
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
    public String muokkaaTuote(@PathVariable("id") Long id, @RequestParam String nimi, @RequestParam String kuva,
            @RequestParam BigDecimal hinta,
            @RequestParam String kuvaus, @RequestParam Long osastoID,
            @RequestParam Long toimittajaID,
            @RequestParam Long valmistajaID) {
        Tuote tuote = tuoteService.getTuoteById(id);
        tuote.setNimi(nimi);
        tuote.setHinta(hinta);
        tuote.setKuva(kuva);
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

}
