package com.services;

import com.models.Depense;
import com.models.LigneCredit;
import com.utils.Connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

public class LigneCreditService {
    
    public void addLigneCredit(LigneCredit ligneCredit) throws Exception {
        try (Connection conn = Connexion.getConnection()) {
            String sql = "INSERT INTO ligne_credit (libelle, montant) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, ligneCredit.getLibelle());
            stmt.setDouble(2, ligneCredit.getMontant());
            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new Exception("La création de la ligne de crédit a échoué");
            }
            
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                ligneCredit.setId(generatedKeys.getInt(1));
            } else {
                throw new Exception("La création de la ligne de crédit a échoué, aucun ID obtenu");
            }
        } catch (SQLException e) {
            throw new Exception("Erreur lors de l'ajout de la ligne de crédit: " + e.getMessage());
        }
    }
    
    public void addDepense(Depense depense) throws Exception {
        try (Connection conn = Connexion.getConnection()) {
            // Vérifier d'abord si la ligne de crédit existe
            String checkSql = "SELECT montant FROM ligne_credit WHERE id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, depense.getLigneCreditId());
            ResultSet rs = checkStmt.executeQuery();
            
            if (!rs.next()) {
                throw new Exception("La ligne de crédit spécifiée n'existe pas");
            }
            
            double ligneCreditMontant = rs.getDouble("montant");
            
            String sumSql = "SELECT COALESCE(SUM(montant), 0) AS total FROM depense WHERE ligne_credit_id = ?";
            PreparedStatement sumStmt = conn.prepareStatement(sumSql);
            sumStmt.setInt(1, depense.getLigneCreditId());
            ResultSet sumRs = sumStmt.executeQuery();
            sumRs.next();
            double totalDepense = sumRs.getDouble("total");
            
            if (totalDepense + depense.getMontant() > ligneCreditMontant) {
                throw new Exception("La dépense dépasse le montant disponible dans la ligne de crédit");
            }
            
            String sql = "INSERT INTO depense (ligne_credit_id, date, montant) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, depense.getLigneCreditId());
            stmt.setDate(2, Date.valueOf(depense.getDate()));
            stmt.setDouble(3, depense.getMontant());
            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new Exception("La création de la dépense a échoué");
            }
            
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                depense.setId(generatedKeys.getInt(1));
            } else {
                throw new Exception("La création de la dépense a échoué, aucun ID obtenu");
            }
        } catch (SQLException e) {
            throw new Exception("Erreur lors de l'ajout de la dépense: " + e.getMessage());
        }
    }
}
