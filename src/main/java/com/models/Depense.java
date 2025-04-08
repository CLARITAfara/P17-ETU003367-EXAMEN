package com.models;

import java.time.LocalDate;

public class Depense {
    private int id;
    private int ligneCreditId;
    private LocalDate date;
    private double montant;
    
    public Depense(int id, int ligneCreditId, LocalDate date, double montant) {
        this.id = id;
        this.ligneCreditId = ligneCreditId;
        this.date = date;
        this.montant = montant;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getLigneCreditId() {
        return ligneCreditId;
    }
    
    public void setLigneCreditId(int ligneCreditId) {
        this.ligneCreditId = ligneCreditId;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public double getMontant() {
        return montant;
    }
    
    public void setMontant(double montant) {
        this.montant = montant;
    }
}
