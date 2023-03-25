package com.example.nkk.models;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
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
public class Product extends AbstractPersistable<Long> { // luo long-pääavaimet automaattisesti luokkaan

    private String name;
    private String description;
    private BigDecimal price;
    // private String kuva;
    // tuote kuuluu yhteen osastoon
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Basic(fetch = FetchType.EAGER)
    private byte[] image;
    @ManyToOne
    private ProductCategory productCategory;
    // tuote kuuluu yhteen valmistajaan
    @ManyToOne
    private Manufacturer manufacturer;
    // tuote kuuluu yhteen toimittajaan
    @ManyToOne
    private Supplier supplier;

}
