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

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String nom = request.getParameter("nom");
        String code = request.getParameter("code");
        TournoiDAO tournoiDAO = new TournoiDAO();
        if ("ajouter".equals(action)) {
            tournoiDAO.ajouterTournoi(new Tournoi(nom, code));
        } else if ("editer".equals(action)) {
            String idParam = request.getParameter("id");
            int id = (idParam != null && !idParam.isEmpty()) ? Integer.parseInt(idParam) : 0;
            Tournoi tournoi = new Tournoi(nom, code);
            tournoiDAO.editerTournoi(tournoi);
        } else if ("supprimer".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            tournoiDAO.supprimerTournoi(id);
        }
        response.sendRedirect("index.jsp");
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String query = request.getParameter("query");
        TournoiDAO tournoiDAO = new TournoiDAO();
        if (action == null || action.isEmpty() || "liste".equals(action)) {
            request.setAttribute("tournois", tournoiDAO.getTournoi());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else if ("rechercher".equals(action)) {
            request.setAttribute("tournois", tournoiDAO.rechercherTournois(query));
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
