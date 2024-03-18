package com.example.revisiontennis.controller;

import com.example.revisiontennis.dao.JoueurDAO;
import com.example.revisiontennis.model.Joueur;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/JoueurServlet")
public class JoueurServlet extends HttpServlet {

    @Override
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
    @Override
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

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        JoueurDAO joueurDAO = new JoueurDAO();
        joueurDAO.supprimerJoueur(id);
        response.sendRedirect("joueur.jsp");
    }

    @Override
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
        response.sendRedirect("editerJoueur.jsp");

    }

}


