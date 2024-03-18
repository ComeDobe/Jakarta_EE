
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Matchs</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <style>
    body {
      margin: 20px;
    }

    table {
      width: 100%;
      margin-top: 20px;
    }

    .search-bar {
      width: 80%;
      margin: 20px auto;
    }

    h1 {
      margin-top: 50px;
      font-size: 36px;
    }
  </style>
</head>
<body>
<div class="text-center">
  <h1>Liste des match</h1>
  <form action="match" method="get" class="form-inline d-flex justify-content-center search-bar">
    <input type="hidden" name="action" value="rechercher">
    <input type="text" name="recherche" placeholder="Recherche..." class="form-control mr-sm-2" style="width: 70%;">
    <input type="submit" value="Rechercher" class="btn btn-primary">
  </form>
</div>
<table class="table table-bordered table-striped">
  <thead>
  <tr>
    <th>Vainqueur - Nom</th>
    <th>Vainqueur - Prénom</th>
    <th>Finaliste - Nom</th>
    <th>Finaliste - Prénom</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="match" items="${match}">
    <tr>
      <td>${joueurs[match.id_vainqueur].nom}</td>
      <td>${joueurs[match.id_vainqueur].prenom}</td>
      <td>${joueurs[match.id_finaliste].nom}</td>
      <td>${joueurs[match.id_finaliste].prenom}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
  <div class="container">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item"><a class="nav-link" href="joueur.jsp">Joueurs</a></li>
        <li class="nav-item"><a class="nav-link" href="tournoi.jsp">Tournois</a></li>
        <li class="nav-item"><a class="nav-link" href="epreuve.jsp">Epreuves</a></li><br><br><br><br><br><br>
        <li>  <a href="LogoutServlet" class="btn btn-danger"><i class="fas fa-sign-out-alt"></i> Déconnexion</a></li>
      </ul>
    </div>
  </div>
</nav>
</body>
</html>
















