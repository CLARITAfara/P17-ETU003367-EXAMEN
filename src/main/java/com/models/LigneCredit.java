package com.models;

public class LigneCredit {
    private int id;
    private String libelle;
    private double montant;
    
    public LigneCredit(int id, String libelle, double montant) {
        this.id = id;
        this.libelle = libelle;
        this.montant = montant;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getLibelle() {
        return libelle;
    }
    
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
    public double getMontant() {
        return montant;
    }
    
    public void setMontant(double montant) {
        this.montant = montant;
    }
}
