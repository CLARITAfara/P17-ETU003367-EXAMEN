package com.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.models.LigneCredit;
import com.utils.Connexion;

public class LigneCreditDAO {
    public void addLigneCredit(LigneCredit ligneCredit, Connection conn) throws SQLException {
        String sql = "INSERT INTO ligne_credit (libelle, montant) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ligneCredit.getLibelle());
            stmt.setDouble(2, ligneCredit.getMontant());
            stmt.executeUpdate();
        }
    }

    public void addLigneCredit(LigneCredit ligneCredit) throws SQLException {
        try (Connection conn = Connexion.getConnection()) {
            addLigneCredit(ligneCredit, conn);
        }
    }

    public LigneCredit findById(int id, Connection conn) throws SQLException {
        String sql = "SELECT * FROM ligne_credit WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new LigneCredit(
                        rs.getInt("id"),
                        rs.getString("libelle"),
                        rs.getDouble("montant")
                    );
                }
                return null;
            }
        }
    }

    public LigneCredit findById(int id) throws SQLException {
        try (Connection conn = Connexion.getConnection()) {
            return findById(id, conn);
        }
    }
}