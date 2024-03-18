<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des joueurs</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        <a class="navbar-brand" href="#">Mon site</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="joueur.jsp">Joueurs</a></li>
                <li class="nav-item"><a class="nav-link" href="tournoi.jsp">Tournois</a></li>
                <li class="nav-item"><a class="nav-link" href="match.jsp">Matchs</a></li>
            </ul>
        </div>
    </nav>
</header>
<main class="container mt-5">
    <h1 class="text-center mb-4">Epreuves des Joueurs</h1>
    <form method="GET" action="epreuve" class="mb-4">
        <div class="form-row">
            <div class="col">
                <select name="annee" id="annee" class="form-control">
                    <c:forEach var="annee" begin="2010" end="2021">
                        <option value="${annee}">${annee}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col">
                <select name="type_epreuve" id="type_epreuve" class="form-control">
                    <option value="F">F</option>
                    <option value="H">H</option>
                </select>
            </div>
            <div class="col-auto">
                <button type="submit" class="btn btn-primary">Rechercher</button>
            </div>
        </div>
    </form>
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th>Nom</th>
            <th>Prénom</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="joueur" items="${joueurs}">
            <tr>
                <td>${joueur.getNom()}</td>
                <td>${joueur.getPrenom()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>

<footer class="bg-light py-3">
    <div class="container">
        <div class="row">
            <div class="col text-center">
            </div>
        </div>
    </div>
</footer>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
    <div class="container">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a class="nav-link" href="joueur.jsp">Joueurs</a></li>
                <li class="nav-item"><a class="nav-link" href="tournoi.jsp">Tournois</a></li>
                <li class="nav-item"><a class="nav-link" href="match.jsp">Matchs</a></li><br><br><br><br><br><br>
                <li> <a href="LogoutServlet" class="btn btn-danger"><i class="fas fa-sign-out-alt"></i> Déconnexion</a></li>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>
