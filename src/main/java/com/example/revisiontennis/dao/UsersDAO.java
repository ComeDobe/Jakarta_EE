package com.example.revisiontennis.dao;

import com.example.revisiontennis.model.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {

    private static Connection connection;
    public UsersDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&serverTimezone=UTC", "root", "");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Erreur lors de l'établissement de la connexion à la base de données", e);
        }
    }
    public static List<Users> getUsers() {
        List<Users> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM users";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                users.add(new Users(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getInt("profil")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public static List<Users> rechercherUsers(Integer id, String username, String password, String email, String profil) {
        List<Users> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM users WHERE id = ? OR username LIKE ? OR password LIKE ? OR email LIKE ? OR profil = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, "%" + username + "%");
            ps.setString(3, "%" + password + "%");
            ps.setString(4, "%" + email + "%");
            ps.setInt(5, Integer.parseInt(profil));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(new Users(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getInt("profil")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public static void ajouterUsers(String username, String password, String email, String profil) {
        try {
            String sql = "INSERT INTO users (username, password, email, profil) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setInt(4, Integer.parseInt(profil));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void editerUsers(Integer id, String username, String password, String email, String profil) {
        try {
            String sql = "UPDATE users SET username = ?, password = ?, email = ?, profil = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setInt(4, Integer.parseInt(profil));
            ps.setInt(5, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void supprimerUsers(Integer id) {
        try {
            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
