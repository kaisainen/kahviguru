package com.example.nkk.repositories;

import java.util.Collection;
import java.util.List;

import com.example.nkk.models.Tuote;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface TuoteRepo extends PagingAndSortingRepository<Tuote, Long> {

        Page<Tuote> findByOsasto_IdIn(Collection<Long> tuotteet, Pageable pageable);

        @Query("SELECT t FROM Tuote t WHERE lower(t.nimi) LIKE %:searchTerm%"
                        + " AND t.osasto.id IN :osastot")
        Page<Tuote> haeTuotteet(@Param("searchTerm") String searchTerm, @Param("osastot") List<Long> osastot,
                        Pageable pageable);

}
