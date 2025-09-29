package model;

public class Utilisateur {
    // Attributs de la classe Utilisateur
    private int id;
    private String nom;
    private String role;

    // Constructeur complet
    public Utilisateur(int id, String nom, String role) {
        this.id = id;
        this.nom = nom;
        this.role = role;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getRole() {
        return role;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Méthode toString pour l'affichage des informations de l'utilisateur
    @Override
    public String toString() {
        return "Utilisateur {" +
                "ID=" + id +
                ", Nom='" + nom + '\'' +
                ", Role='" + role + '\'' +
                '}';
    }
}
