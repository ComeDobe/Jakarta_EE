package com.example.revisontennis.dao;

import com.example.revisontennis.model.Joueur;

import java.sql.*;

public class JoueurDAO {
private Joueur joueur;
private Connection connection;

public JoueurDAO() {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&serverTimezone=UTC", "root", "root");
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
    }
}
    public void ajouterJoueur(Joueur joueur) throws SQLException {

        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO joueur (nom, prenom, sexe) VALUES (?, ?, ?)");
            ps.setString(1, joueur.getNom());
            ps.setString(2, joueur.getPrenom());
            ps.setString(3, joueur.getSexe());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void editerJoueur(Joueur joueur) {

            try {
                PreparedStatement ps = connection.prepareStatement("UPDATE joueur SET nom = ?, prenom = ?, sexe = ? WHERE id = ?");
                ps.setString(1, joueur.getNom());
                ps.setString(2, joueur.getPrenom());
                ps.setString(3, joueur.getSexe());
                ps.setInt(4, joueur.getId());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public void supprimerJoueur(int id) {

            try {
                PreparedStatement ps = connection.prepareStatement("DELETE FROM joueur WHERE id = ?");
                ps.setInt(1, id);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public Object rechercherJoueurs(String query, String sexe) {
            try {
                PreparedStatement ps;
                if (sexe.isEmpty()) {
                    ps = connection.prepareStatement("SELECT * FROM joueur WHERE nom LIKE ? OR prenom LIKE ?");
                    ps.setString(1, "%" + query + "%");
                    ps.setString(2, "%" + query + "%");
                } else {
                    ps = connection.prepareStatement("SELECT * FROM joueur WHERE (nom LIKE ? OR prenom LIKE ?) AND sexe = ?");
                    ps.setString(1, "%" + query + "%");
                    ps.setString(2, "%" + query + "%");
                    ps.setString(3, sexe);
                }
                ResultSet rs = ps.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return joueur;
    }

    public Object getJoueur() {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM joueur");
            ResultSet rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return joueur;
    }

    public Joueur getJoueurById(int idVainqueur) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM joueur WHERE id = ?");
            ps.setInt(1, idVainqueur);
            ResultSet rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
    }
        return null;
    }
}
