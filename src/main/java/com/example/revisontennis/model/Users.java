package com.example.revisontennis.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
public class Users {
    private  Integer Id;
    private String username;
    private String password;
    private String email;
    private int profil;

    public Users(Integer id, String username, String password, String email, int profil) {
        Id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.profil = profil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users users = (Users) o;
        return getId() == users.getId() && getProfil() == users.getProfil() &&
                Objects.equals(getUsername(), users.getUsername()) &&
                Objects.equals(getPassword(), users.getPassword()) &&
                Objects.equals(getEmail(), users.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword(), getEmail(), getProfil());
    }

}
