package com.example.nkk;

import java.util.List;

import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OsastoService {

    @Autowired
    private OsastoRepo osastoRepo;

    public List<Osasto> listaaOsastot() {
        return osastoRepo.findAll();
    }

    public Osasto getOsastoById(long id) {
        return osastoRepo.getById(id);
    }

}
