package model;

import java.time.LocalDate;

public class Emprunt {
    // Attributs de la classe Emprunt
    private int id;
    private int utilisateurID; // Référence à l'ID de l'utilisateur
    private int livreID;       // Référence à l'ID du livre
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourEffective;

    // Constructeur complet
    public Emprunt(int id, int utilisateurID, int livreID, LocalDate dateEmprunt, LocalDate dateRetourPrevue, LocalDate dateRetourEffective) {
        this.id = id;
        this.utilisateurID = utilisateurID;
        this.livreID = livreID;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
        this.dateRetourEffective = dateRetourEffective;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getUtilisateurID() {
        return utilisateurID;
    }

    public int getLivreID() {
        return livreID;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public LocalDate getDateRetourPrevue() {
        return dateRetourPrevue;
    }

    public LocalDate getDateRetourEffective() {
        return dateRetourEffective;
    }

    // Setters
    public void setDateRetourEffective(LocalDate dateRetourEffective) {
        this.dateRetourEffective = dateRetourEffective;
    }

    // Méthode toString pour afficher les informations de l'emprunt
    @Override
    public String toString() {
        return "Emprunt {" +
                "ID=" + id +
                ", UtilisateurID=" + utilisateurID +
                ", LivreID=" + livreID +
                ", Date Emprunt=" + dateEmprunt +
                ", Date Retour Prévue=" + dateRetourPrevue +
                ", Date Retour Effective=" + (dateRetourEffective != null ? dateRetourEffective : "En cours") +
                '}';
    }
}
