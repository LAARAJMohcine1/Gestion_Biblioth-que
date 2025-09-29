package controller;

import model.Emprunt;
import model.Livre;
import model.Utilisateur;
import utils.CSVUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpruntController {
    private static final String FICHIER_EMPRUNTS = "emprunts.csv";

    // Récupérer tous les emprunts depuis le fichier CSV
    public List<Emprunt> getEmprunts() {
        List<Emprunt> emprunts = new ArrayList<>();
        List<String[]> lignes = CSVUtils.lireCSV(FICHIER_EMPRUNTS);

        for (int i = 1; i < lignes.size(); i++) { // Ignorer l'en-tête
            String[] ligne = lignes.get(i);
            if (ligne.length == 6) { // Vérification de la structure des lignes
                emprunts.add(new Emprunt(
                        Integer.parseInt(ligne[0]), // ID
                        Integer.parseInt(ligne[1]), // UtilisateurID
                        Integer.parseInt(ligne[2]), // LivreID
                        LocalDate.parse(ligne[3]),  // DateEmprunt
                        LocalDate.parse(ligne[4]),  // DateRetourPrevue
                        ligne[5].equals("NULL") ? null : LocalDate.parse(ligne[5]) // DateRetourEffective
                ));
            }
        }
        return emprunts;
    }

    // Enregistrer un nouvel emprunt dans le fichier CSV
    public void enregistrerEmprunt(Utilisateur utilisateur, Livre livre, LocalDate dateRetourPrevue) {
        int id = getEmprunts().size() + 1; // Générer un nouvel ID basé sur la taille actuelle
        String[] nouvelleLigne = {
                String.valueOf(id),                    // ID
                String.valueOf(utilisateur.getId()),   // ID Utilisateur
                String.valueOf(livre.getId()),         // ID Livre
                LocalDate.now().toString(),            // Date d'emprunt (aujourd'hui)
                dateRetourPrevue.toString(),           // Date retour prévue
                "NULL"                                 // Date retour effective (null par défaut)
        };

        CSVUtils.ajouterLigneCSV(FICHIER_EMPRUNTS, nouvelleLigne);
    }

    // Mettre à jour la date de retour effective pour un emprunt existant
    public void enregistrerRetour(int idEmprunt) {
        List<String[]> lignes = CSVUtils.lireCSV(FICHIER_EMPRUNTS);

        for (int i = 1; i < lignes.size(); i++) { // Ignorer l'en-tête
            if (Integer.parseInt(lignes.get(i)[0]) == idEmprunt) { // Trouver l'emprunt par ID
                lignes.get(i)[5] = LocalDate.now().toString(); // Mettre à jour la date de retour effective
                break;
            }
        }

        CSVUtils.ecrireCSV(FICHIER_EMPRUNTS, lignes); // Réécrire le fichier CSV avec les modifications
    }
    public void supprimerEmprunt(int id) {
        List<String[]> lignes = CSVUtils.lireCSV(FICHIER_EMPRUNTS); // Lire le fichier CSV
        lignes.removeIf(ligne -> ligne.length > 0 && ligne[0] != null && ligne[0].matches("\\d+") && Integer.parseInt(ligne[0]) == id);
        CSVUtils.ecrireCSV(FICHIER_EMPRUNTS, lignes); // Écrire les données mises à jour
    }


    // Convertir les emprunts pour affichage dans une vue
    public Object[][] getEmpruntsData() {
        List<Emprunt> emprunts = getEmprunts();
        Object[][] data = new Object[emprunts.size()][6];

        for (int i = 0; i < emprunts.size(); i++) {
            Emprunt e = emprunts.get(i);
            data[i] = new Object[]{
                    e.getId(),
                    e.getUtilisateurID(),
                    e.getLivreID(),
                    e.getDateEmprunt(),
                    e.getDateRetourPrevue(),
                    (e.getDateRetourEffective() == null) ? "En cours" : e.getDateRetourEffective()
            };
        }
        return data;
    }
}
