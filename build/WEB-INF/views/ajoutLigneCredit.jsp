<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajout Ligne Crédit</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1>Ajout Ligne Crédit</h1>
        
        <form action="ajoutLigneCredit" method="post" class="mt-4">
            <div class="mb-3">
                <label for="libelle" class="form-label">Libellé :</label>
                <input type="text" class="form-control" id="libelle" name="libelle" required>
            </div>
            
            <div class="mb-3">
                <label for="montant" class="form-label">Montant :</label>
                <input type="number" step="0.01" class="form-control" id="montant" name="montant" required>
            </div>
            
            <button type="submit" class="btn btn-primary">Enregistrer</button>
        </form>
        
        <% if (request.getAttribute("erreur") != null) { %>
            <div class="alert alert-danger mt-3">
                ${erreur}
            </div>
        <% } %>
        
        <div class="mt-3">
            <a href="dashboard" class="btn btn-secondary">Retour au Dashboard</a>
        </div>
    </div>
</body>
</html>
