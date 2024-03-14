package com.example.revisiontennis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
public class Tournoi {

    @Id
    private Integer id;
    private String nom;
    private String code;

    public Tournoi(String nom, String code) {
        this.id = id;
        this.nom = nom;
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tournoi)) return false;
        Tournoi tournoi = (Tournoi) o;
        return getId().equals(tournoi.getId()) && getNom().equals(tournoi.getNom()) && getCode().equals(tournoi.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNom(), getCode());
    }
}
