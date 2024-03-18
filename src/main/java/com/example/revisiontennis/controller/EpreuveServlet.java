package com.example.revisiontennis.controller;

import com.example.revisiontennis.dao.EpreuveDAO;
import com.example.revisiontennis.model.Epreuve;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/epreuve")
public class EpreuveServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        EpreuveDAO epreuveDAO = new EpreuveDAO();

        if ("ajouter".equals(action)) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            Integer id_tournoi = Integer.parseInt(req.getParameter("id_tournoi"));
            String type = req.getParameter("type");
            Epreuve epreuve = new Epreuve(id, id_tournoi, type);
            epreuveDAO.ajouterEpreuve(epreuve);
        } else if ("editer".equals(action)) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            Integer id_tournoi = Integer.parseInt(req.getParameter("id_tournoi"));
            String type = req.getParameter("type");
            Epreuve epreuve = new Epreuve(id, id_tournoi, type);
            epreuveDAO.editerEpreuve(epreuve);
        } else if ("supprimer".equals(action)) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            epreuveDAO.supprimerEpreuve(id);
        }
        resp.sendRedirect("epreuve.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String annee = req.getParameter("annee");
        String type_epreuve = req.getParameter("type_epreuve");
        EpreuveDAO epreuveDAO = new EpreuveDAO();

        if (action == null || action.isEmpty() || "liste".equals(action)) {
            req.setAttribute("epreuves", epreuveDAO.getEpreuve());
            req.getRequestDispatcher("epreuve.jsp").forward(req, resp);
        } else if ("rechercher".equals(action)) {
            req.setAttribute("epreuves", epreuveDAO.rechercherEpreuves(annee, type_epreuve));
            req.getRequestDispatcher("epreuve.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String idStr = req.getParameter("id");

        if (idStr != null && !idStr.isEmpty()) {
            try {
                Integer id = Integer.parseInt(idStr);
                EpreuveDAO epreuveDAO = new EpreuveDAO();
                epreuveDAO.supprimerEpreuve(id);
                resp.sendRedirect("epreuve.jsp");
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Identifiant de l'épreuve invalide");
            }
        } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Identifiant de l'épreuve manquant");
            }

        }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        Integer id_tournoi = Integer.parseInt(req.getParameter("id_tournoi"));
        String type = req.getParameter("type");
        Epreuve epreuve = new Epreuve(id, id_tournoi, type);
        EpreuveDAO epreuveDAO = new EpreuveDAO();
        epreuveDAO.ajouterEpreuve(epreuve);
        resp.sendRedirect("epreuve.jsp");
    }
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        Integer id_tournoi = Integer.parseInt(req.getParameter("id_tournoi"));
        String type = req.getParameter("type");
        Epreuve epreuve = new Epreuve(id, id_tournoi, type);
        EpreuveDAO epreuveDAO = new EpreuveDAO();
        epreuveDAO.editerEpreuve(epreuve);
        resp.sendRedirect("epreuve.jsp");
    }

}
