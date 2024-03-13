package com.example.revisontennis.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
public class Match {
    private Integer id;
    private int id_vainqueur;
    private int id_finaliste;

    public Match(Integer id, int id_vainqueur, int id_finaliste) {
        this.id = id;
        this.id_vainqueur = id_vainqueur;
        this.id_finaliste = id_finaliste;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Match)) return false;
        Match match = (Match) o;
        return getId_vainqueur() == match.getId_vainqueur() && getId_finaliste() == match.getId_finaliste() && getId().equals(match.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getId_vainqueur(), getId_finaliste());
    }
}
