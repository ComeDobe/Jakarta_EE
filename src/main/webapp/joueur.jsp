
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>joueurs</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    <link href="joueur.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1 class="my-3">Liste des joueurs</h1>
    <form action="JoueurServlet" method="get" class="search-form">
        <input type="hidden" name="action" value="rechercher">
        <div class="form-group">
            <input type="text" name="query" id="query" class="form-control" placeholder="Recherche ">
        </div>
        <div class="form-group">
            <select name="sexe" id="sexe" class="form-control">
                <option value="">Tous</option>
                <option value="M">M</option>
                <option value="F">F</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary"><i class="fas fa-search"></i> Rechercher</button>
    </form>
    <div class="table-responsive">
        <table class="table table-bordered">
            <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Sexe</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="joueur" items="${joueurs}">
                <tr>
                    <td>${joueur.id}</td>
                    <td>${joueur.nom}</td>
                    <td>${joueur.prenom}</td>
                    <td>${joueur.sexe}</td>
                    <td>
                        <div class="btn-group" role="group">
                            <a href="JoueurServlet?action=editer&id=${joueur.id}" class="btn btn-warning btn-sm"><i class="fas fa-edit"></i> Éditer</a>
                            <a href="JoueurServlet?action=supprimer&id=${joueur.id}" class="btn btn-danger btn-sm" id="supprimer-${joueur.id}"><i class="fas fa-trash"></i>Supprimer</a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <form action="JoueurServlet" method="post" class="add-form">
        <div class="form-row">
            <div class="form-group col-md-4">
                <input type="text" name="nom" id="nom" class="form-control" placeholder="Nom" required>
            </div>
            <div class="form-group col-md-4">
                <input type="text" name="prenom" id="prenom" class="form-control" placeholder="Prénom" required>
            </div>
            <div class="form-group col-md-2">
                <select name="sexe" id="sexe" class="form-control" required>
                    <option value="M">M</option>
                    <option value="F">F</option>
                </select>
            </div>
            <div class="form-group col-md-2">
                <button type="submit" class="btn btn-success"><i class="fas fa-plus"></i> Ajouter</button>
            </div>
        </div>
        <input type="hidden" name="action" value="ajouter">
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.min.js"></script>
<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
    <div class="container">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a class="nav-link" href="tournoi.jsp">Tournois</a></li>
                <li class="nav-item"><a class="nav-link" href="match.jsp">Matchs</a></li>
                <li class="nav-item">
                    <a class="nav-link" href="epreuve.jsp">Epreuves</a>
                </li><br><br><br><br><br><br>
                <li>   <a href="LogoutServlet" class="btn btn-danger"><i class="fas fa-sign-out-alt"></i> Déconnexion</a></li>
            </ul>
        </div>
    </div>
</nav>
<script src="joueur.js"></script>
</body>
</html>


