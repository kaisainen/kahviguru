package com.example.nkk.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.AbstractPersistable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Osasto extends AbstractPersistable<Long> {

    private String nimi;
    private Long osastoIDP;

    // osastossa voi olla monta tuotetta. TUOTTEET OMISTAA SUHTEEN, ei osasto
    @OneToMany(mappedBy = "osasto")
    private List<Tuote> tuotteet = new ArrayList<>();
}
