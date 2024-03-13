package com.example.revisontennis.controller;

import com.example.revisontennis.dao.UsersDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

public class UsersServlet extends HttpServlet {
    private UsersDAO usersDAO;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        Integer id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String profil = request.getParameter("profil");
        if (action == null || action.isEmpty() || "liste".equals(action)) {
            request.setAttribute("users", UsersDAO.getUsers());
            request.getRequestDispatcher("users.jsp").forward(request, response);
        } else if ("rechercher".equals(action)) {
            request.setAttribute("users", UsersDAO.rechercherUsers(id, username, password, email, profil));
            request.getRequestDispatcher("users.jsp").forward(request, response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        UsersDAO usersDAO = new UsersDAO();

        if ("ajouter".equals(action)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            String email = request.getParameter("email");
            String profil = request.getParameter("profil");

            UsersDAO.ajouterUsers(username, hashedPassword, email, profil);
        } else if ("editer".equals(action)) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            String email = request.getParameter("email");
            String profil = request.getParameter("profil");

            UsersDAO.editerUsers(id, username, hashedPassword, email, profil);
        }
        response.sendRedirect("users.jsp");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        UsersDAO usersDAO = new UsersDAO();
        usersDAO.supprimerUsers(id);
        response.sendRedirect("users.jsp");
    }
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String profil = request.getParameter("profil");
        UsersDAO usersDAO = new UsersDAO();
        usersDAO.editerUsers(id, username, password, email, profil);
        response.sendRedirect("users.jsp");
    }

}
