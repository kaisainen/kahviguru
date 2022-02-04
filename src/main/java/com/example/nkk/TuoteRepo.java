package com.example.nkk;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TuoteRepo extends JpaRepository<Tuote, Long> {

    List<Tuote> findByOsasto_idIn(Collection<Long> tuotteet);

}
