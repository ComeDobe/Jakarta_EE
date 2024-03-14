package com.example.revisiontennis.dao;

import com.example.revisiontennis.model.Match;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatchDAO {
    private Connection connection;

    public MatchDAO() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&serverTimezone=UTC", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Match> rechercherMatches(String recherche) {
        List<Match> matches = new ArrayList<>();
        String sql = "SELECT * FROM match_tennis WHERE id_vainqueur IN (SELECT id FROM joueur WHERE nom LIKE ? OR prenom LIKE ?) OR id_finaliste IN (SELECT id FROM joueur WHERE nom LIKE ? OR prenom LIKE ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            String rechercheSql = "%" + recherche + "%";
            statement.setString(1, rechercheSql);
            statement.setString(2, rechercheSql);
            statement.setString(3, rechercheSql);
            statement.setString(4, rechercheSql);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    matches.add(new Match(
                            resultSet.getInt("id"),
                            resultSet.getInt("id_vainqueur"),
                            resultSet.getInt("id_finaliste")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return matches;
    }

    public void ajouterMatch(Match match) {
        String sql = "INSERT INTO match_tennis (id_vainqueur, id_finaliste) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, match.getId_vainqueur());
            ps.setInt(2, match.getId_finaliste());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editerMatch(Match match) {
        String sql = "UPDATE match_tennis SET id_vainqueur = ?, id_finaliste = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, match.getId_vainqueur());
            ps.setInt(2, match.getId_finaliste());
            ps.setInt(3, match.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void supprimerMatch(int id) {
        String sql = "DELETE FROM match_tennis WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Match getMatch() throws SQLException {
        String sql = "SELECT * FROM match_tennis WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt("id"));
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    return new Match(
                            resultSet.getInt("id"),
                            resultSet.getInt("id_vainqueur"),
                            resultSet.getInt("id_finaliste")
                    );
                }
            }
        }
        return  new Match(0, 0, 0);
}

    public List<Match> listerMatchs() {
        List<Match> matches = new ArrayList<>();
        String sql = "SELECT * FROM match_tennis";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                matches.add(new Match(
                        resultSet.getInt("id"),
                        resultSet.getInt("id_vainqueur"),
                        resultSet.getInt("id_finaliste")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return matches;
    }

    public List<Match> rechercherMatchs() {
        return rechercherMatches("");
    }
}
