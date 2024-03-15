<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Ajouter un tournoi</title>
</head>
<body>
<h1>Ajouter un tournoi</h1>
<form action="TournoisServlet" method="post">
  <input type="hidden" name="action" value="ajouter">
  <label for="nom">Nom:</label>
  <input type="text" name="nom" required><br><br>
  <label for="code">Code:</label>
  <input type="text" name="code" required><br><br>
  <button type="submit">Ajouter</button>
</form>
<br>
<a href="index.jsp">Retour à la liste des tournois</a>
<p>
  Le nom du tournoi est : ${param.nom}<br>
  Le code du tournoi est : ${param.code}<br>
</p>
</body>
</html>