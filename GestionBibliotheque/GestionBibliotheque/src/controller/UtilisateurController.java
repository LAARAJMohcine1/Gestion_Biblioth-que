package controller;

import model.Utilisateur;
import utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurController {
    private static final String FICHIER_UTILISATEURS = "utilisateurs.csv";

    // Récupérer tous les utilisateurs depuis le fichier CSV
    public List<Utilisateur> getUtilisateurs() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        List<String[]> lignes = CSVUtils.lireCSV(FICHIER_UTILISATEURS);

        for (int i = 1; i < lignes.size(); i++) { // Ignorer l'en-tête
            String[] ligne = lignes.get(i);
            if (ligne.length == 3) { // ID, Nom, Rôle
                utilisateurs.add(new Utilisateur(
                        Integer.parseInt(ligne[0]), // ID
                        ligne[1],                  // Nom
                        ligne[2]                   // Rôle
                ));
            }
        }
        return utilisateurs;
    }

    // Ajouter un nouvel utilisateur dans le fichier CSV
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        String[] nouvelleLigne = {
                String.valueOf(utilisateur.getId()),
                utilisateur.getNom(),
                utilisateur.getRole()
        };
        CSVUtils.ajouterLigneCSV(FICHIER_UTILISATEURS, nouvelleLigne);
    }

    // Rechercher un utilisateur par nom
    public Utilisateur findByName(String nom) {
        for (Utilisateur utilisateur : getUtilisateurs()) {
            if (utilisateur.getNom().equalsIgnoreCase(nom)) {
                return utilisateur;
            }
        }
        return null; // Aucun utilisateur trouvé
    }

    // Supprimer un utilisateur par ID
    public void supprimerUtilisateur(int id) {
        List<String[]> lignes = CSVUtils.lireCSV(FICHIER_UTILISATEURS);
        lignes.removeIf(ligne -> ligne.length > 0 && ligne[0] != null && ligne[0].matches("\\d+") && Integer.parseInt(ligne[0]) == id);
        CSVUtils.ecrireCSV(FICHIER_UTILISATEURS, lignes);
    }



    // Afficher les utilisateurs dans la console (pour tests)
    public void afficherUtilisateurs() {
        List<Utilisateur> utilisateurs = getUtilisateurs();
        System.out.println("=== Liste des Utilisateurs ===");
        for (Utilisateur user : utilisateurs) {
            System.out.println("ID: " + user.getId() + ", Nom: " + user.getNom() + ", Rôle: " + user.getRole());
        }
    }
}
