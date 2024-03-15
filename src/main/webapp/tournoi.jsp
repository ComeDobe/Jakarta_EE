
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des tournois</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <style>
        th {
            text-align: center;
            background-color: black;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2
        }

        .action-btns {
            display: flex;
            justify-content: space-between;
        }

        .action-btns a {
            margin: 0 5px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="text-center mb-5">Liste des tournoi</h1>
    <form method="get" action="tournoi" class="form-inline mb-3">
        <div class="form-group mr-2">
            <label for="recherche">Rechercher :</label>
            <input type="text" class="form-control ml-2" id="recherche" name="recherche" value="${param.recherche}">
        </div>
        <button type="submit" class="btn btn-primary">Rechercher</button>
    </form>
    <hr>
    <table class="table table-striped table-bordered">
        <thead>
        <tr>
            <th>Id</th>
            <th>Nom</th>
            <th>Code</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="tournoi" items="${tournoi}">
            <tr>
                <td>${tournoi.id}</td>
                <td>${tournoi.nom}</td>
                <td>${tournoi.code}</td>
                <td class="action-btns">
                    <a href="tournoi?action=editer&id=${tournoi.id}" class="btn btn-sm btn-primary">Editer</a>
                    <a href="tournoi?action=supprimer&id=${tournoi.id}" class="btn btn-sm btn-danger">Supprimer</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <hr>
    <a href="tournoi?action=ajouter" class="btn btn-success">Ajouter un tournoi</a>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1/14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
    <div class="container">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a class="nav-link" href="joueur.js">Joueurs</a></li>
                <li class="nav-item"><a class="nav-link" href="match.jsp">Matchs</a></li>
                <li class="nav-item"><a class="nav-link" href="epreuve.jsp">Epreuves</a></li><br><br><br><br><br><br>
                <li>  <a href="LogoutServlet" class="btn btn-danger"><i class="fas fa-sign-out-alt"></i> Déconnexion</a></li>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>