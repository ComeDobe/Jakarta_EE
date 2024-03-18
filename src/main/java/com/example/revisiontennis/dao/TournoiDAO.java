package com.example.revisiontennis.dao;

import com.example.revisiontennis.model.Tournoi;
import jakarta.servlet.ServletException;

import java.sql.*;
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

    public List<Tournoi> rechercherTournois(String recherche) {
        List<Tournoi> tournois = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tournoi WHERE nom LIKE ? OR code LIKE ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            String querySql = "%" + recherche + "%";
            ps.setString(1, querySql);
            ps.setString(2, querySql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Tournoi tournoi = new Tournoi(rs.getString("nom"), rs.getString("code"));
                tournoi.setId(rs.getInt("id"));
                tournoi.setNom(rs.getString("nom"));
                tournoi.setCode(rs.getString("code"));

                tournois.add(tournoi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la recherche des tournois", e);
        }
        return tournois;
    }


    public List<Tournoi> getTournoi() {
        List<Tournoi> tournois = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tournoi";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Tournoi tournoi = new Tournoi(rs.getString("nom"), rs.getString("code"));
                tournoi.setId(rs.getInt("id"));
                tournois.add(tournoi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération des tournois", e);
        }
        return tournois;
    }

}
