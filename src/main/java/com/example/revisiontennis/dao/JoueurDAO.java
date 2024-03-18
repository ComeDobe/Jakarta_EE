package com.example.revisiontennis.dao;

import com.example.revisiontennis.model.Joueur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoueurDAO {
private Joueur joueur;
private Connection connection;

    public JoueurDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&serverTimezone=UTC", "root", "");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Erreur lors de l'établissement de la connexion à la base de données", e);
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

    public List<Joueur> rechercherJoueurs(String query, String sexe) {
        List<Joueur> joueurs = new ArrayList<>();
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

            while (rs.next()) {
                Joueur joueur = new Joueur( rs.getString("nom"), rs.getString("prenom"), rs.getString("sexe"));
                joueur.setId(rs.getInt("id"));
                joueur.setNom(rs.getString("nom"));
                joueur.setPrenom(rs.getString("prenom"));
                joueur.setSexe(rs.getString("sexe"));
                joueurs.add(joueur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return joueurs;
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
