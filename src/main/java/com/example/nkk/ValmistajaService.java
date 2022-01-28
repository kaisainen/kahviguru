package com.example.nkk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValmistajaService {

    @Autowired
    private ValmistajaRepo valmistajaRepo;
}
