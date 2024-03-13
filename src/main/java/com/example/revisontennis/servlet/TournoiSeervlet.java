package com.example.revisontennis.servlet;

import com.example.revisontennis.dao.TournoiDAO;
import com.example.revisontennis.model.Tournoi;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/TournoiServlet")
public class TournoiSeervlet extends HttpServlet {

    private TournoiDAO tournoiDAO;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String code = request.getParameter("code");
        Tournoi tournoi = new Tournoi(nom, code);
        tournoiDAO.ajouterTournoi(tournoi);
        response.sendRedirect("tournois.jsp");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String query = request.getParameter("query");
        if (action == null || action.isEmpty() || "liste".equals(action)) {
            request.setAttribute("tournois", tournoiDAO.getTournoi());
            request.getRequestDispatcher("tournois.jsp").forward(request, response);
        } else if ("rechercher".equals(action)) {
            request.setAttribute("tournois", tournoiDAO.rechercherTournois(query));
            request.getRequestDispatcher("tournois.jsp").forward(request, response);
        }
    }
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        tournoiDAO.supprimerTournoi(id);
        response.sendRedirect("tournois.jsp");
    }
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String code = request.getParameter("code");
        Tournoi tournoi = new Tournoi(nom, code);
        tournoiDAO.ajouterTournoi(tournoi);
        response.sendRedirect("tournois.jsp");
    }
    public void doPatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String code = request.getParameter("code");
        Tournoi tournoi = new Tournoi(nom, code);
        tournoiDAO.editerTournoi(tournoi);
        response.sendRedirect("tournois.jsp");
    }
}
