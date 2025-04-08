package com.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.models.Depense;
import com.services.LigneCreditService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AjoutDepenseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/ajoutDepense.jsp").forward(req, resp);
    }
   
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int ligneCreditId = Integer.parseInt(req.getParameter("ligneCreditId"));
            String dateStr = req.getParameter("date");
            LocalDate date = LocalDate.parse(dateStr);
            double montant = Double.parseDouble(req.getParameter("montant"));
           
            Depense depense = new Depense(0, ligneCreditId, date, montant);
            LigneCreditService service = new LigneCreditService();
           
            service.addDepense(depense);
            resp.sendRedirect("dashboard");  
        } catch (NumberFormatException e) {
            req.setAttribute("erreur", "Format de nombre invalide");
            req.getRequestDispatcher("/WEB-INF/views/ajoutDepense.jsp").forward(req, resp);
        } catch (DateTimeParseException e) {
            req.setAttribute("erreur", "Format de date invalide");
            req.getRequestDispatcher("/WEB-INF/views/ajoutDepense.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("erreur", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/ajoutDepense.jsp").forward(req, resp);
        }
    }
}
