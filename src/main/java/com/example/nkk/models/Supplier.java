package com.example.nkk.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supplier extends AbstractPersistable<Long> {

    private String name;
    private String contactPersonName;
    private String contactEmail;

    // toimittajassa voi olla monta tuotetta. TUOTTEET OMISTAA SUHTEEN
    @OneToMany(mappedBy = "supplier")
    private List<Product> products = new ArrayList<>();
}
