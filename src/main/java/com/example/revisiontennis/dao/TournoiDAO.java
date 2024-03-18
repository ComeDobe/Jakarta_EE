package com.example.revisiontennis.dao;

import com.example.revisiontennis.model.Tournoi;
import jakarta.servlet.ServletException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TournoiDAO {

    Connection connection;

    public TournoiDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&serverTimezone=UTC", "root", "");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Erreur lors de l'établissement de la connexion à la base de données", e);
        }
    }

    public void ajouterTournoi(Tournoi tournoi) {
        try {
            String sql = "INSERT INTO tournoi (nom, code) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, tournoi.getNom());
            ps.setString(2, tournoi.getCode());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editerTournoi(Tournoi tournoi) {
        try {
            String sql = "UPDATE tournoi SET nom = ?, code = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, tournoi.getNom());
            ps.setString(2, tournoi.getCode());
            ps.setInt(3, tournoi.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void supprimerTournoi(int id) {
        try {
            String sql = "DELETE FROM tournoi WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Tournoi getTournoi() {
        return null;
    }

    public List < Tournoi> rechercherTournois(String recherche) {
        List<Tournoi> tournois = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tournoi WHERE nom LIKE ? OR code LIKE ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            String querySql = "%" + recherche + "%";
            ps.setString(1, querySql);
            ps.setString(2, querySql);
            ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tournois;
    }

}
