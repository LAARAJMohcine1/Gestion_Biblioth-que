package controller;

import model.Livre;
import utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class LivreController {
    private static final String FICHIER_LIVRES = "livres.csv";

    // Récupérer tous les livres depuis le fichier CSV
    public List<Livre> getLivres() {
        List<Livre> livres = new ArrayList<>();
        List<String[]> lignes = CSVUtils.lireCSV(FICHIER_LIVRES);

        for (int i = 1; i < lignes.size(); i++) { // Ignorer l'en-tête
            String[] ligne = lignes.get(i);
            if (ligne.length == 5) { // Vérifiez que chaque ligne a exactement 5 colonnes
                livres.add(new Livre(
                        Integer.parseInt(ligne[0]), // ID
                        ligne[1],                  // Titre
                        ligne[2],                  // Auteur
                        Integer.parseInt(ligne[3]), // Année
                        ligne[4]                   // Genre
                ));
            }
        }
        return livres;
    }

    // Ajouter un nouveau livre dans le fichier CSV
    public void ajouterLivre(Livre livre) {
        String[] nouvelleLigne = {
                String.valueOf(livre.getId()),
                livre.getTitre(),
                livre.getAuteur(),
                String.valueOf(livre.getAnneePublication()),
                livre.getGenre()
        };
        CSVUtils.ajouterLigneCSV(FICHIER_LIVRES, nouvelleLigne);
    }

    // Supprimer un livre par index (ligne sélectionnée)
    public void supprimerLivre(int index) {
        List<String[]> lignes = CSVUtils.lireCSV(FICHIER_LIVRES);

        // Vérifier que l'index est valide
        if (index >= 1 && index < lignes.size()) { // Ignorer l'en-tête
            lignes.remove(index);
            CSVUtils.ecrireCSV(FICHIER_LIVRES, lignes);
        } else {
            System.out.println("Index invalide pour suppression !");
        }
    }

    // Rechercher des livres selon un critère (Titre, Auteur ou Genre)
    public List<Livre> rechercherLivres(String critere, String recherche) {
        List<Livre> resultats = new ArrayList<>();
        for (Livre livre : getLivres()) {
            if ((critere.equalsIgnoreCase("Titre") && livre.getTitre().equalsIgnoreCase(recherche)) ||
                (critere.equalsIgnoreCase("Auteur") && livre.getAuteur().equalsIgnoreCase(recherche)) ||
                (critere.equalsIgnoreCase("Genre") && livre.getGenre().equalsIgnoreCase(recherche))) {
                resultats.add(livre);
            }
        }
        if (resultats.isEmpty()) {
            System.out.println("Aucun livre trouvé pour le critère : " + critere + " = " + recherche);
        }
        return resultats;
    }

    // Convertir les livres en tableau pour l'affichage dans les vues
    public Object[][] convertirLivresEnDonnees(List<Livre> livres) {
        Object[][] data = new Object[livres.size()][5];
        for (int i = 0; i < livres.size(); i++) {
            Livre livre = livres.get(i);
            data[i] = new Object[]{
                    livre.getId(),
                    livre.getTitre(),
                    livre.getAuteur(),
                    livre.getAnneePublication(),
                    livre.getGenre()
            };
        }
        return data;
    }
}
