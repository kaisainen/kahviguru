package com.example.nkk;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OsastoRepo extends JpaRepository<Osasto, Long> {

    public Osasto getById(long id);

}
