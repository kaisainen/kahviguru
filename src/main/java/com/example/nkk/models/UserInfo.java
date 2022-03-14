package com.example.nkk.models;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo extends AbstractPersistable<Long> {

    private String username;
    private String password;
    // private Boolean isADmin;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> authorities;
}
