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
public class Manufacturer extends AbstractPersistable<Long> {

    private String name;
    private String url;

    // valmistajassa voi olla monta tuotetta. TUOTTEET OMISTAA SUHTEEN
    @OneToMany(mappedBy = "manufacturer")
    private List<Product> products = new ArrayList<>();
}