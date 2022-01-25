package com.example.nkk;

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
public class Valmistaja extends AbstractPersistable<Long> {

    private String nimi;
    private String url;

    // valmistajassa voi olla monta tuotetta. TUOTTEET OMISTAA SUHTEEN
    @OneToMany(mappedBy = "valmistaja")
    private List<Tuote> tuotteet = new ArrayList<>();
}
