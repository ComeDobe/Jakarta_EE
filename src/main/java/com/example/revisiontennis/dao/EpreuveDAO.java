package com.example.revisiontennis.dao;

import com.example.revisiontennis.model.Epreuve;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EpreuveDAO {
    static Connection connection;

    public EpreuveDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&serverTimezone=UTC", "root", "");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Erreur lors de l'établissement de la connexion à la base de données", e);
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

    public static List<Epreuve> getEpreuve() {
        List<Epreuve> epreuves = new ArrayList<>();
        try {
            String sql = "SELECT * FROM epreuve";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Epreuve epreuve = new Epreuve(rs.getInt( "id"), rs.getInt("id_tournoi"), rs.getString("type_epreuve"));
                epreuve.setId(rs.getInt("id"));
                epreuve.setType_epreuve(rs.getString("type_epreuve"));
                epreuve.setAnnee(rs.getInt("annee"));
                epreuves.add(epreuve);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération des épreuves", e);
        }
        return epreuves;
    }


    public static List<Epreuve> rechercherEpreuves(String query, String type_epreuve) {
        List<Epreuve> epreuves = new ArrayList<>();
        try {
            String sql = "SELECT * FROM epreuve WHERE type_epreuve LIKE ? OR annee LIKE ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + type_epreuve + "%");
            ps.setString(2, "%" + query + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Epreuve epreuve = new Epreuve( rs.getInt("id"), rs.getInt("id_tournoi"), rs.getString("type_epreuve"));
                epreuves.add(epreuve);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la recherche des épreuves", e);
        }
        return epreuves;
    }

}
