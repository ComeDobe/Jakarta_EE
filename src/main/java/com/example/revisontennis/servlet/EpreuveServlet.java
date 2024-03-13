package com.example.revisontennis.servlet;

import com.example.revisontennis.dao.EpreuveDAO;
import com.example.revisontennis.model.Epreuve;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/EpreuveServlet")
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
        if (action == null || action.isEmpty() || "liste".equals(action)) {
            req.setAttribute("epreuves", EpreuveDAO.getEpreuve());
            req.getRequestDispatcher("epreuve.jsp").forward(req, resp);
        } else if ("rechercher".equals(action)) {
            req.setAttribute("epreuves", EpreuveDAO.rechercherEpreuves(annee, type_epreuve));
            req.getRequestDispatcher("epreuve.jsp").forward(req, resp);
        }

    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        EpreuveDAO epreuveDAO = new EpreuveDAO();
        epreuveDAO.supprimerEpreuve(id);
        resp.sendRedirect("epreuve.jsp");
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
