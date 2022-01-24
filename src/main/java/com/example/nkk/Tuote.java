package com.example.nkk;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // luo tietokantataulun
@Data // luo getterit ja setterit
@NoArgsConstructor // luo konstruktorit
@AllArgsConstructor // luo konstruktorit
public class Tuote extends AbstractPersistable<Long> { // luo long-pääavaimet automaattisesti luokkaan

    private String nimi;
    private String kuvaus;
    private BigDecimal hinta;
    private String kuva;
    // tuote kuuluu yhteen osastoon
    @ManyToOne
    private Osasto osasto;
    // tuote kuuluu yhteen valmistajaan
    @ManyToOne
    private Valmistaja valmistaja;
    // tuote kuuluu yhteen toimittajaan
    @ManyToOne
    private Toimittaja toimittaja;

}
