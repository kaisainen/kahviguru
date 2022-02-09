package com.example.nkk.repositories;

import java.util.List;

import com.example.nkk.models.Osasto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OsastoRepo extends JpaRepository<Osasto, Long> {

    Osasto findByNimi(String nimi);
}
