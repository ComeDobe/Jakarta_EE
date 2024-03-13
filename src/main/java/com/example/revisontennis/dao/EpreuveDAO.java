package com.example.revisontennis.dao;

import com.example.revisontennis.model.Epreuve;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EpreuveDAO {
    static Connection connection;

    public EpreuveDAO() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&serverTimezone=UTC", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void ajouterEpreuve(Epreuve epreuve) {
        try {
            String sql = "INSERT INTO epreuve (type_epreuve, annee) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, epreuve.getTypeEpreuve());
            ps.setInt(2, epreuve.getAnnee());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editerEpreuve(Epreuve epreuve) {
        try {
            String sql = "UPDATE epreuve SET type_epreuve = ?, annee = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, epreuve.getTypeEpreuve());
            ps.setInt(2, epreuve.getAnnee());
            ps.setInt(3, epreuve.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void supprimerEpreuve(Integer id) {
        try {
            String sql = "DELETE FROM epreuve WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List <Epreuve> getEpreuve() {
        try {
            String sql = "SELECT * FROM epreuve";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static List<Epreuve> rechercherEpreuves(String query, String type_epreuve) {
        List<Epreuve> epreuves = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM epreuve WHERE type_epreuve LIKE ? OR annee LIKE ?");
            ps.setString(1, "%" + query + "%");
            ps.setString(2, "%" + query + "%");
            ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return epreuves;
    }
}
