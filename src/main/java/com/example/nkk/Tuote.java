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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tuote extends AbstractPersistable<Long> {

    private String nimi;
    private String kuvaus;
    private BigDecimal hinta;
    private String kuva;

    @ManyToOne
    private Osasto osasto;
    @ManyToOne
    private Valmistaja valmistaja;
    @ManyToOne
    private Toimittaja toimittaja;

}
