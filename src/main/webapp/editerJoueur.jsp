<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifier un joueur</title>
    <!-- Ajout de Bootstrap CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css"
          rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }

        .container {
            margin-top: 30px;
        }

        h1 {
            text-align: center;
            font-weight: bold;
        }

        form {
            margin-top: 30px;
        }

        label {
            font-weight: bold;
        }

        .form-group {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>editer un joueur</h1>

    <form action="JoueurServlet" method="post">
        <input type="hidden" name="action" value="editer">
        <input type="hidden" name="id" value="${joueur.id}">

        <div class="form-group">
            <label for="nom">Nom :</label>
            <input type="text" name="nom" id="nom" class="form-control" value="${joueur.nom}">
        </div>

        <div class="form-group">
            <label for="prenom">Prénom :</label>
            <input type="text" name="prenom" id="prenom" class="form-control" value="${joueur.prenom}">
        </div>

        <div class="form-group">
            <label for="sexe">Sexe :</label>
            <select name="sexe" id="sexe" class="form-control">
                <option value="M" ${joueur.sexe == 'M' ? 'selected' : ''}>M</option>
                <option value="F" ${joueur.sexe == 'F' ? 'selected' : ''}>F</option>
            </select>
        </div>

        <button type="submit" class="btn btn-primary"><i class="fas fa-save"></i> Enregistrer</button>
    </form>
</div>
<!-- Ajout de jQuery et de Bootstrap JavaScript -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.min.js"></script>
</body>
</html>
