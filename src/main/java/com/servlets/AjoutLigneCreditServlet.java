package com.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.models.LigneCredit;
import com.services.LigneCreditService;

import java.io.IOException;

public class AjoutLigneCreditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/ajoutLigneCredit.jsp").forward(req, resp);
    }
   
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String libelle = req.getParameter("libelle");
            double montant = Double.parseDouble(req.getParameter("montant"));
           
            LigneCredit ligneCredit = new LigneCredit(0, libelle, montant);
            LigneCreditService service = new LigneCreditService();
           
            service.addLigneCredit(ligneCredit);
            resp.sendRedirect("dashboard");  
        } catch (NumberFormatException e) {
            req.setAttribute("erreur", "Format du montant invalide");
            req.getRequestDispatcher("/WEB-INF/views/ajoutLigneCredit.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("erreur", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/ajoutLigneCredit.jsp").forward(req, resp);
        }
    }
}