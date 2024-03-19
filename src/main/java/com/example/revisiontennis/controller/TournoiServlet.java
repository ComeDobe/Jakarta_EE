package com.example.revisiontennis.controller;

import com.example.revisiontennis.dao.TournoiDAO;
import com.example.revisiontennis.model.Tournoi;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet( "/tournoi")
public class TournoiServlet extends HttpServlet {


    private TournoiDAO tournoiDAO;
@Override
public void init() throws ServletException {
    super.init();
    tournoiDAO= new TournoiDAO();
}
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String code = request.getParameter("code");
        Tournoi tournoi = new Tournoi(nom, code);
        tournoiDAO.ajouterTournoi(tournoi);

        try {
           tournoiDAO.ajouterTournoi(tournoi);
        } catch (Exception e) {

        }
        if (nom == null || nom.isEmpty() || code == null || code.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Paramètres invalides");
            return;
        }
        else if ("editer".equals(request.getParameter("action"))) {
            tournoiDAO.editerTournoi(tournoi);
        } else if ("supprimer".equals(request.getParameter("action"))) {
            tournoiDAO.supprimerTournoi(tournoi.getId());
        }
        else if ("ajouter".equals(request.getParameter("action"))) {
            tournoiDAO.ajouterTournoi(tournoi);
        }

        response.sendRedirect("tournoi.jsp");

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String query = request.getParameter("query");
        if (action == null || action.isEmpty() || "liste".equals(action)) {
            request.setAttribute("tournoi", tournoiDAO.getTournoi());
            request.getRequestDispatcher("tournoi.jsp").forward(request, response);
        } else if ("rechercher".equals(action)) {
            request.setAttribute("tournoi", tournoiDAO.rechercherTournois(query));
            request.getRequestDispatcher("tournoi.jsp").forward(request, response);
        }
    }
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                tournoiDAO.supprimerTournoi(id);
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String code = request.getParameter("code");
        Tournoi tournoi = new Tournoi(nom, code);
        tournoiDAO.ajouterTournoi(tournoi);
        if (nom == null || nom.isEmpty() || code == null || code.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Paramètres invalides");
            return;
        }
        else if ("editer".equals(request.getParameter("action"))) {
            tournoiDAO.editerTournoi(tournoi);
        } else if ("supprimer".equals(request.getParameter("action"))) {
            tournoiDAO.supprimerTournoi(tournoi.getId());
        }
        else if ("ajouter".equals(request.getParameter("action"))) {
            tournoiDAO.ajouterTournoi(tournoi);
        }
        response.sendRedirect("tournoi.jsp");

    }

    protected void doPatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String code = request.getParameter("code");
        Tournoi tournoi = new Tournoi(nom, code);
        tournoiDAO.editerTournoi(tournoi);
        if (nom == null || nom.isEmpty() || code == null || code.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Paramètres invalides");
            return;
        }
        response.sendRedirect("tournoi.jsp");
    }

}
