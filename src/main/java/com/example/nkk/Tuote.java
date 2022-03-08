package com.example.nkk;

import java.math.BigDecimal;

import javax.persistence.Entity;
<<<<<<< Updated upstream:src/main/java/com/example/nkk/Tuote.java
<<<<<<< Updated upstream:src/main/java/com/example/nkk/Tuote.java
=======
=======
>>>>>>> Stashed changes:src/main/java/com/example/nkk/models/Tuote.java
import javax.persistence.Lob;
>>>>>>> Stashed changes:src/main/java/com/example/nkk/models/Tuote.java
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
<<<<<<< Updated upstream:src/main/java/com/example/nkk/Tuote.java
<<<<<<< Updated upstream:src/main/java/com/example/nkk/Tuote.java
    private String kuva;
=======
=======
>>>>>>> Stashed changes:src/main/java/com/example/nkk/models/Tuote.java
    @Lob
    // @Type(type = "org.hibernate.type.BinaryType")
    // @Basic(fetch = FetchType.EAGER)
    private byte[] kuva;

<<<<<<< Updated upstream:src/main/java/com/example/nkk/Tuote.java
>>>>>>> Stashed changes:src/main/java/com/example/nkk/models/Tuote.java
=======
>>>>>>> Stashed changes:src/main/java/com/example/nkk/models/Tuote.java
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
