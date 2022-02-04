package com.example.nkk;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

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
    // private String kuva;
    // tuote kuuluu yhteen osastoon
    @Lob
    private byte[] kuva;
    @ManyToOne
    private Osasto osasto;
    // tuote kuuluu yhteen valmistajaan
    @ManyToOne
    private Valmistaja valmistaja;
    // tuote kuuluu yhteen toimittajaan
    @ManyToOne
    private Toimittaja toimittaja;

}
