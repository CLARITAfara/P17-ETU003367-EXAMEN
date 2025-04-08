package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.models.Depense;
import com.utils.Connexion;

public class DepenseDAO {
    public void addDepense(Depense depense, Connection conn) throws SQLException {
        String sql = "INSERT INTO depense (ligne_credit_id, date, montant) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, depense.getLigneCreditId());
            stmt.setDate(2, java.sql.Date.valueOf(depense.getDate()));
            stmt.setDouble(3, depense.getMontant());
            stmt.executeUpdate();
        }
    }

    public void addDepense(Depense depense) throws SQLException {
        try (Connection conn = Connexion.getConnection()) {
            addDepense(depense, conn);
        }
    }

    public Depense findById(int id, Connection conn) throws SQLException {
        String sql = "SELECT * FROM depense WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Depense(
                        rs.getInt("id"),
                        rs.getInt("ligne_credit_id"),
                        rs.getDate("date").toLocalDate(),
                        rs.getDouble("montant")
                    );
                }
                return null;
            }
        }
    }

    public Depense findById(int id) throws SQLException {
        try (Connection conn = Connexion.getConnection()) {
            return findById(id, conn);
        }
    }

    public void deleteDepense(int id, Connection conn) throws SQLException {
        String sql = "DELETE FROM depense WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void deleteDepense(int id) throws SQLException {
        try (Connection conn = Connexion.getConnection()) {
            deleteDepense(id, conn);
        }
    }

    public double getSommeDepense(int ligneCreditId, Connection conn) throws SQLException {
        String sql = "SELECT SUM(montant) AS total FROM depense WHERE ligne_credit_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ligneCreditId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("total");
                }
                return 0;
            }
        }
    }
}