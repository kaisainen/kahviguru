package com.example.nkk;

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
    private OsastoRepo osastoRepo;

    @Autowired
    private TuoteRepo tuoteRepo;

    @Autowired
    private ValmistajaRepo valmistajaRepo;

    @Autowired
    private ToimittajaRepo toimittajaRepo;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/tuotteet")
    public String naytaTuotteet(Model model) {
        model.addAttribute("tuotteet", tuoteRepo.findAll());
        return "tuotteet";
    }

    @GetMapping("/valmistajat")
    public String naytaValmistajat(Model model) {
        model.addAttribute("valmistajat", valmistajaRepo.findAll());
        return "valmistajat";
    }

    @GetMapping("/toimittajat")
    public String naytaToimittajat(Model model) {
        model.addAttribute("toimittajat", toimittajaRepo.findAll());
        return "toimittajat";
    }

    @PostMapping("/valmistajat")
    public String lisaaValmistaja(@RequestParam String nimi, @RequestParam String url) {
        Valmistaja uusiValmistaja = new Valmistaja();
        uusiValmistaja.setNimi(nimi);
        uusiValmistaja.setUrl(url);
        valmistajaRepo.save(uusiValmistaja);
        return "redirect:/valmistajat";
    }

    @PostMapping("/toimittajat")
    public String lisaaToimittaja(@RequestParam String nimi, @RequestParam String yhteyshenkilo,
            @RequestParam String yhteyshenkiloemail) {
        Toimittaja uusToimittaja = new Toimittaja();
        uusToimittaja.setNimi(nimi);
        uusToimittaja.setYhteyshenkilo(yhteyshenkilo);
        uusToimittaja.setYhteyshenkiloemail(yhteyshenkiloemail);
        toimittajaRepo.save(uusToimittaja);
        return "redirect:/toimittajat";
    }

    @GetMapping("/valmistajat/muokkaa/{id}")
    public String naytaMuokkaaValmistaja(@PathVariable("id") long id, Model model) {
        Valmistaja valmistaja = valmistajaRepo.getById(id);
        model.addAttribute("valmistaja", valmistaja);
        return "muokkaa";
    }

    @PostMapping("/valmistajat/muokkaa/{id}")
    public String muokkaaValmistajaa(@PathVariable("id") Long id, @RequestParam String nimi, @RequestParam String url) {
        Valmistaja valmistaja = valmistajaRepo.getById(id);
        valmistaja.setNimi(nimi);
        valmistaja.setUrl(url);
        valmistajaRepo.save(valmistaja);
        return "redirect:/valmistajat";
    }

    @RequestMapping(value = "valmistajat/poista", method = RequestMethod.GET)
    public String poistaValmistaja(@RequestParam(name = "valmistajaId") Long id) {
        valmistajaRepo.deleteById(id);
        return "redirect:/valmistajat";
    }

    @RequestMapping(value = "toimittajat/poista", method = RequestMethod.GET)
    public String poistaToimittaja(@RequestParam(name = "toimittajaId") Long id) {
        toimittajaRepo.deleteById(id);
        return "redirect:/toimittajat";
    }

    // @PostMapping("/tuotteet")
    // public String lisaaTuote(@RequestParam String nimi, @RequestParam String
    // @RequestParam String toimittaja,
    // @RequestParam String osasto) {

    // if (!nimi.isEmpty() && !kuvaus.isEmpty()) {
    // Tuote uusiTuote = new Tuote();
    // uusiTuote.setNimi(nimi);
    // uusiTuote.setHinta(hinta);
    // uusiTuote.setKuva(kuva);
    // uusiTuote.setKuvaus(kuvaus);
    // Osasto uusiOsasto = new Osasto();
    // uusiOsasto.setNimi("nimi1");
    // uusiOsasto.setOsastoIDP(2L);
    // uusiOsasto.setTuotteet(new ArrayList<>());
    // osastoRepo.save(uusiOsasto);
    // Valmistaja uusiValmistaja = new Valmistaja();
    // uusiValmistaja.setNimi("nimiv");
    // uusiValmistaja.setTuotteet(new ArrayList<>());
    // uusiValmistaja.setUrl("www.hs.fi");
    // valmistajaRepo.save(uusiValmistaja);
    // Toimittaja uusiToimittaja = new Toimittaja();
    // uusiToimittaja.setNimi("nimi3");
    // uusiToimittaja.setYhteyshenkilo("kaarlo");
    // uusiToimittaja.setTuotteet(new ArrayList<>());
    // uusiToimittaja.setYhteyshenkiloemail("kaarlo@email.com");
    // toimittajaRepo.save(uusiToimittaja);
    // uusiTuote.setToimittaja(uusiToimittaja);
    // uusiTuote.setValmistaja(uusiValmistaja);
    // uusiTuote.setOsasto(uusiOsasto);
    // tuoteRepo.save(uusiTuote);
    // }
    // return "redirect:/tuotteet";
    // }

}
