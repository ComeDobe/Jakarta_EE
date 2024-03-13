package com.example.revisontennis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
public class Joueur {
    @Id
    private Integer id;
    private String nom;
    private String prenom;
    private String sexe;

    public Joueur(Integer id, String nom, String prenom, String sexe) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Joueur)) return false;
        Joueur joueur = (Joueur) o;
        return getId().equals(joueur.getId()) &&
                getNom().equals(joueur.getNom()) &&
                getPrenom().equals(joueur.getPrenom()) &&
                getSexe().equals(joueur.getSexe());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNom(), getPrenom(), getSexe());
    }
}
