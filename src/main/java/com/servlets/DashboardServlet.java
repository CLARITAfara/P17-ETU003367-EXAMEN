package com.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.utils.Connexion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection conn = Connexion.getConnection()) {
            String sql = "SELECT lc.id, lc.libelle, lc.montant, COALESCE(SUM(d.montant), 0) AS sommeDepense " +
                         "FROM ligne_credit lc " +
                         "LEFT JOIN depense d ON lc.id = d.ligne_credit_id " +
                         "GROUP BY lc.id, lc.libelle, lc.montant";
                         
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            req.setAttribute("lignesCredit", rs);
            req.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(req, resp);
        } catch (SQLException e) {
            req.setAttribute("erreur", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(req, resp);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
