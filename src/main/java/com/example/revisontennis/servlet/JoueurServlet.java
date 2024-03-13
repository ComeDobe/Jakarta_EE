package com.example.revisontennis.servlet;

import com.example.revisontennis.dao.JoueurDAO;
import com.example.revisontennis.model.Joueur;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/JoueurServlet")
public class JoueurServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String query = request.getParameter("query");
        String sexe = request.getParameter("sexe");
        JoueurDAO joueurDAO = new JoueurDAO();
        if (action == null || action.isEmpty() || "liste".equals(action)) {
            request.setAttribute("joueurs", joueurDAO.getJoueur());
            request.getRequestDispatcher("joueur.jsp").forward(request, response);
        } else if ("rechercher".equals(action)) {
            request.setAttribute("joueurs", joueurDAO.rechercherJoueurs(query, sexe));
            request.getRequestDispatcher("joueur.jsp").forward(request, response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String sexe = request.getParameter("sexe");
        JoueurDAO joueurDAO = new JoueurDAO();
        Joueur joueur = new Joueur(nom, prenom, sexe);
        if ("ajouter".equals(action)) {
            try {
                joueurDAO.ajouterJoueur(joueur);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if ("editer".equals(action)) {
            String idParam = request.getParameter("id");
            int id = (idParam != null && !idParam.isEmpty()) ? Integer.parseInt(idParam) : 0;
            joueur.setId(id);
            joueurDAO.editerJoueur(joueur);
        } else if ("supprimer".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            joueurDAO.supprimerJoueur(id);
        }
        response.sendRedirect("joueur.jsp");
    }


    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        JoueurDAO joueurDAO = new JoueurDAO();
        joueurDAO.supprimerJoueur(id);
        response.sendRedirect("joueur.jsp");
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String sexe = request.getParameter("sexe");
        Joueur joueur = new Joueur(nom, prenom, sexe);
        JoueurDAO joueurDAO = new JoueurDAO();
        try {
            joueurDAO.ajouterJoueur(joueur);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("joueur.jsp");
    }

    protected void doPatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String sexe = request.getParameter("sexe");
        Joueur joueur = new Joueur(nom, prenom, sexe);
        joueur.setId(id);
        JoueurDAO joueurDAO = new JoueurDAO();
        joueurDAO.editerJoueur(joueur);
        response.sendRedirect("joueur.jsp");
    }

}
