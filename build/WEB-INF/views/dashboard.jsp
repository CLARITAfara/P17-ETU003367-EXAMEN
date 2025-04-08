<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1>Dashboard</h1>
        
        <div class="mt-3 mb-3">
            <a href="ajoutLigneCredit" class="btn btn-primary me-2">Ajouter Ligne Crédit</a>
            <a href="ajoutDepense" class="btn btn-success">Ajouter Dépense</a>
        </div>
        
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID Ligne Crédit</th>
                    <th>Libellé</th>
                    <th>Montant</th>
                    <th>Somme Dépensée</th>
                    <th>Reste</th>
                </tr>
            </thead>
            <tbody>
                <% 
                ResultSet lignesCredit = (ResultSet) request.getAttribute("lignesCredit");
                if (lignesCredit != null) {
                    while (lignesCredit.next()) { 
                %>
                    <tr>
                        <td><%= lignesCredit.getInt("id") %></td>
                        <td><%= lignesCredit.getString("libelle") %></td>
                        <td><%= lignesCredit.getDouble("montant") %></td>
                        <td><%= lignesCredit.getDouble("sommeDepense") %></td>
                        <td><%= lignesCredit.getDouble("montant") - lignesCredit.getDouble("sommeDepense") %></td>
                    </tr>
                <% 
                    }
                }
                %>
            </tbody>
        </table>

        <% if (request.getAttribute("erreur") != null) { %>
            <div class="alert alert-danger mt-3">
                ${erreur}
            </div>
        <% } %>
    </div>
</body>
</html>
