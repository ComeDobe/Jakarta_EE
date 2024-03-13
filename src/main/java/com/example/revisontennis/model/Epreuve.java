package com.example.revisontennis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
@Entity
@Getter
@Setter
public class Epreuve {

    @Id
    private Integer id;
    private int annee;
    private String type_epreuve;
    private int id_tournoi;

    public Epreuve(Integer id, int annee, String type_epreuve) {
        this.id = id;
        this.annee = annee;
        this.type_epreuve = type_epreuve;
        this.id_tournoi = id_tournoi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Epreuve)) return false;
        Epreuve epreuve = (Epreuve) o;
        return getAnnee() == epreuve.getAnnee() &&
                getId_tournoi() == epreuve.getId_tournoi() &&
                getId().equals(epreuve.getId()) &&
                getType_epreuve().equals(epreuve.getType_epreuve());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAnnee(), getType_epreuve(), getId_tournoi());
    }

    public String getTypeEpreuve() {
        return type_epreuve;
    }
}
