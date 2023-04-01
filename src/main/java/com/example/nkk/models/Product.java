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

@Entity // creates a table
@Data // creates getters and setters
@NoArgsConstructor // creates constructors
@AllArgsConstructor // creates constructors
public class Product extends AbstractPersistable<Long> { // creates long ids automatically

    private String name;
    private String description;
    private BigDecimal price;
    // product belongs to only one product category
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Basic(fetch = FetchType.EAGER)
    private byte[] image;
    @ManyToOne
    private ProductCategory productCategory;
    // product belongs to one manufacturer
    @ManyToOne
    private Manufacturer manufacturer;
    // product belongs to one supplier
    @ManyToOne
    private Supplier supplier;

}
