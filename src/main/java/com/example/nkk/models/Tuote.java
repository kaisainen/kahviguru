package com.example.nkk.models;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
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
    @Type(type = "org.hibernate.type.BinaryType")
    @Basic(fetch = FetchType.EAGER)
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
