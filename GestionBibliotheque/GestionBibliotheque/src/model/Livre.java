package model;

public class Livre {
    // Déclaration des attributs
    private int id;
    private String titre;
    private String auteur;
    private int anneePublication;
    private String genre;

    // Constructeur complet
    public Livre(int id, String titre, String auteur, int anneePublication, String genre) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
        this.genre = genre;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public int getAnneePublication() {
        return anneePublication;
    }

    public String getGenre() {
        return genre;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setAnneePublication(int anneePublication) {
        this.anneePublication = anneePublication;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    // Méthode toString pour affichage
    @Override
    public String toString() {
        return "Livre {" +
                "ID=" + id +
                ", Titre='" + titre + '\'' +
                ", Auteur='" + auteur + '\'' +
                ", Année=" + anneePublication +
                ", Genre='" + genre + '\'' +
                '}';
    }
}
