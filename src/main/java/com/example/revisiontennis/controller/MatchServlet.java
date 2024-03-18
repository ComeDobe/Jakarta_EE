package com.example.revisiontennis.controller;

import com.example.revisiontennis.dao.JoueurDAO;
import com.example.revisiontennis.dao.MatchDAO;
import com.example.revisiontennis.model.Joueur;
import com.example.revisiontennis.model.Match;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet( "/match")
public class MatchServlet extends HttpServlet {
    private MatchDAO matchDAO;
    private JoueurDAO joueurDAO;

    @Override
    public void init() {
        this.matchDAO = new MatchDAO();
        this.joueurDAO = new JoueurDAO();
    }

    private Integer safelyParseInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("ajouter".equals(action) || "editer".equals(action)) {
            Integer id = safelyParseInteger(req.getParameter("id"));
            Integer id_vainqueur = safelyParseInteger(req.getParameter("id_vainqueur"));
            Integer id_finaliste = safelyParseInteger(req.getParameter("id_finaliste"));

            if (id == null || id_vainqueur == null || id_finaliste == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Paramètres invalides");
                return;
            }

            Match match = new Match(id, id_vainqueur, id_finaliste);
            if ("ajouter".equals(action)) {
                matchDAO.ajouterMatch(match);
            } else {
                matchDAO.editerMatch(match);
            }
        } else if ("supprimer".equals(action)) {
            Integer id = safelyParseInteger(req.getParameter("id"));
            if (id == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Identifiant du match invalide");
                return;
            }
            matchDAO.supprimerMatch(id);
        }
        resp.sendRedirect("match.jsp");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        matchDAO = new MatchDAO();
        String action = req.getParameter("action");

        if ("rechercher".equals(action)) {
            req.setAttribute("matches", matchDAO.rechercherMatchs(action));
            req.getRequestDispatcher("match.jsp").forward(req, resp);
        } else {
            try {
                req.setAttribute("matches", matchDAO.getMatch());
                req.getRequestDispatcher("match.jsp").forward(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de l'accès à la base de données.");
            }
        }
    }

    @Override
  protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     String idStr = request.getParameter("id");
     if (idStr != null && !idStr.isEmpty()) {
         try {
             Integer id = Integer.parseInt(idStr);
             matchDAO.supprimerMatch(id);
             response.sendRedirect("match.jsp");
         } catch (NumberFormatException e) {
             response.sendError(HttpServletResponse.SC_BAD_REQUEST, "L'identifiant du match est invalide");
         }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "L'identifiant du match est requis");

     }
  }
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            try {
                Integer id = Integer.parseInt(idStr);
                Integer id_vainqueur = Integer.parseInt(request.getParameter("id_vainqueur"));
                Integer id_finaliste = Integer.parseInt(request.getParameter("id_finaliste"));
                Match match = new Match(id, id_vainqueur, id_finaliste);
                matchDAO.ajouterMatch(match);
                response.sendRedirect("match.jsp");
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "L'identifiant du match est invalide");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "L'identifiant du match est requis");
        }

    }

    protected void afficherListeMatchs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Match> matchs = matchDAO.listerMatchs();

        Map<Integer, Joueur> joueurs = new HashMap<>();
        for (Match match : matchs) {
            int id_vainqueur = match.getId_vainqueur();
            int id_finaliste = match.getId_finaliste();

            if (!joueurs.containsKey(id_vainqueur)) {
                joueurs.put(id_vainqueur, joueurDAO.getJoueurById(id_vainqueur));
            }

            if (!joueurs.containsKey(id_finaliste)) {
                joueurs.put(id_finaliste, joueurDAO.getJoueurById(id_finaliste));
            }
        }

        request.setAttribute("matchs", matchs);
        request.setAttribute("joueurs", joueurs);
        request.getRequestDispatcher("match.jsp").forward(request, response);
    }
}
