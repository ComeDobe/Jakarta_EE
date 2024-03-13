package com.example.revisontennis.servlet;

import com.example.revisontennis.dao.JoueurDAO;
import com.example.revisontennis.dao.MatchDAO;
import com.example.revisontennis.model.Joueur;
import com.example.revisontennis.model.Match;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/MatchServlet")
public class MatchServlet extends HttpServlet {
    private MatchDAO matchDAO;
    private JoueurDAO joueurDAO;


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        MatchDAO matchDAO = new MatchDAO();

        if ("ajouter".equals(action)) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            Integer id_vainqueur = Integer.parseInt(req.getParameter("id_vainqueur"));
            Integer id_finaliste = Integer.parseInt(req.getParameter("id_finaliste"));
            Match match = new Match(id, id_vainqueur, id_finaliste);
            matchDAO.ajouterMatch(match);
        } else if ("editer".equals(action)) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            Integer id_vainqueur = Integer.parseInt(req.getParameter("id_vainqueur"));
            Integer id_finaliste = Integer.parseInt(req.getParameter("id_finaliste"));
            Match match = new Match(id, id_vainqueur, id_finaliste);
            matchDAO.editerMatch(match);
        } else if ("supprimer".equals(action)) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            matchDAO.supprimerMatch(id);
        }
        resp.sendRedirect("match.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("query");
        matchDAO = new MatchDAO();
        String action = null;
        if (action == null || action.isEmpty() || "liste".equals(action)) {
            try {
                req.setAttribute("matches", matchDAO.getMatch());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            req.getRequestDispatcher("match.jsp").forward(req, resp);
        } else if ("rechercher".equals(action)) {
            action = req.getParameter("action");

            req.setAttribute("matches", matchDAO.rechercherMatchs());
            req.getRequestDispatcher("match.jsp").forward(req, resp);
        }
    }
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        matchDAO.supprimerMatch(id);
        response.sendRedirect("match.jsp");
    }
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Integer id_vainqueur = Integer.parseInt(request.getParameter("id_vainqueur"));
        Integer id_finaliste = Integer.parseInt(request.getParameter("id_finaliste"));
        Match match = new Match(id, id_vainqueur, id_finaliste);
        matchDAO.ajouterMatch(match);
        response.sendRedirect("match.jsp");
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
