package src;

import controller.LivreController;
import controller.UtilisateurController;
import controller.EmpruntController;
import model.Livre;
import model.Utilisateur;
import model.Emprunt;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Initialisation des contrôleurs
        LivreController livreController = new LivreController();
        UtilisateurController utilisateurController = new UtilisateurController();
        EmpruntController empruntController = new EmpruntController();

        // ======== MENU PRINCIPAL ========
        while (true) {
            String choix = JOptionPane.showInputDialog(null,
                    "=== Gestion de la Bibliothèque ===\n" +
                            "1. Gérer les livres\n" +
                            "2. Gérer les utilisateurs\n" +
                            "3. Gérer les emprunts\n" +
                            "4. Quitter\n\n" +
                            "Veuillez entrer votre choix :");

            if (choix == null || choix.equals("4")) {
                JOptionPane.showMessageDialog(null, "Au revoir !");
                System.exit(0);
            }

            switch (choix) {
                case "1":
                    gererLivres(livreController);
                    break;
                case "2":
                    gererUtilisateurs(utilisateurController);
                    break;
                case "3":
                    gererEmprunts(livreController, utilisateurController, empruntController);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Choix invalide !");
            }
        }
    }

    // ======== MÉTHODE : Gérer les livres ========
    private static void gererLivres(LivreController livreController) {
        while (true) {
            String choix = JOptionPane.showInputDialog(null,
                    "=== Gestion des Livres ===\n" +
                            "1. Afficher tous les livres\n" +
                            "2. Ajouter un livre\n" +
                            "3. Rechercher un livre\n" +
                            "4. Supprimer un livre\n" +
                            "5. Retour au menu principal\n\n" +
                            "Veuillez entrer votre choix :");

            if (choix == null || choix.equals("5")) {
                return;
            }

            switch (choix) {
                case "1": // Afficher les livres
                    List<Livre> livres = livreController.getLivres();
                    StringBuilder affichage = new StringBuilder("=== Liste des Livres ===\n");
                    for (Livre livre : livres) {
                        affichage.append("ID: ").append(livre.getId())
                                .append(", Titre: ").append(livre.getTitre())
                                .append(", Auteur: ").append(livre.getAuteur())
                                .append(", Année: ").append(livre.getAnneePublication())
                                .append(", Genre: ").append(livre.getGenre()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, affichage.toString());
                    break;

                case "2": // Ajouter un livre
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Entrez l'ID du livre :"));
                    String titre = JOptionPane.showInputDialog("Entrez le titre du livre :");
                    String auteur = JOptionPane.showInputDialog("Entrez l'auteur du livre :");
                    int annee = Integer.parseInt(JOptionPane.showInputDialog("Entrez l'année de publication :"));
                    String genre = JOptionPane.showInputDialog("Entrez le genre du livre :");

                    Livre nouveauLivre = new Livre(id, titre, auteur, annee, genre);
                    livreController.ajouterLivre(nouveauLivre);
                    JOptionPane.showMessageDialog(null, "Livre ajouté avec succès !");
                    break;

                case "3": // Rechercher un livre
                    String critere = JOptionPane.showInputDialog("Rechercher par : Titre, Auteur ou Genre ?");
                    String recherche = JOptionPane.showInputDialog("Entrez la valeur de recherche :");

                    List<Livre> resultats = livreController.rechercherLivres(critere, recherche);
                    StringBuilder resultatRecherche = new StringBuilder("=== Résultats de la recherche ===\n");
                    for (Livre livre : resultats) {
                        resultatRecherche.append("ID: ").append(livre.getId())
                                .append(", Titre: ").append(livre.getTitre()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, resultatRecherche.toString());
                    break;

                case "4": // Supprimer un livre
                    int index = Integer.parseInt(JOptionPane.showInputDialog("Entrez l'ID du livre à supprimer :"));
                    livreController.supprimerLivre(index);
                    JOptionPane.showMessageDialog(null, "Livre supprimé avec succès !");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Choix invalide !");
            }
        }
    }

    // ======== MÉTHODE : Gérer les utilisateurs ========
    private static void gererUtilisateurs(UtilisateurController utilisateurController) {
        while (true) {
            String choix = JOptionPane.showInputDialog(null,
                    "=== Gestion des Utilisateurs ===\n" +
                            "1. Afficher tous les utilisateurs\n" +
                            "2. Ajouter un utilisateur\n" +
                            "3. Rechercher un utilisateur\n" +
                            "4. Supprimer un utilisateur\n" +
                            "5. Retour au menu principal\n\n" +
                            "Veuillez entrer votre choix :");

            if (choix == null || choix.equals("5")) {
                return;
            }

            switch (choix) {
                case "1": // Afficher tous les utilisateurs
                    List<Utilisateur> utilisateurs = utilisateurController.getUtilisateurs();
                    StringBuilder affichage = new StringBuilder("=== Liste des Utilisateurs ===\n");
                    for (Utilisateur user : utilisateurs) {
                        affichage.append("ID: ").append(user.getId())
                                .append(", Nom: ").append(user.getNom())
                                .append(", Rôle: ").append(user.getRole()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, affichage.toString());
                    break;

                case "2": // Ajouter un utilisateur
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Entrez l'ID de l'utilisateur :"));
                    String nom = JOptionPane.showInputDialog("Entrez le nom de l'utilisateur :");
                    String role = JOptionPane.showInputDialog("Entrez le rôle de l'utilisateur (Admin/Membre) :");

                    Utilisateur nouvelUtilisateur = new Utilisateur(id, nom, role);
                    utilisateurController.ajouterUtilisateur(nouvelUtilisateur);
                    JOptionPane.showMessageDialog(null, "Utilisateur ajouté avec succès !");
                    break;

                case "3": // Rechercher un utilisateur
                    String nomRecherche = JOptionPane.showInputDialog("Entrez le nom de l'utilisateur à rechercher :");
                    Utilisateur utilisateur = utilisateurController.findByName(nomRecherche);
                    if (utilisateur != null) {
                        JOptionPane.showMessageDialog(null, "Utilisateur trouvé :\n" +
                                "ID: " + utilisateur.getId() +
                                "\nNom: " + utilisateur.getNom() +
                                "\nRôle: " + utilisateur.getRole());
                    } else {
                        JOptionPane.showMessageDialog(null, "Aucun utilisateur trouvé !");
                    }
                    break;

                case "4": // Supprimer un utilisateur
                    int idSuppression = Integer.parseInt(JOptionPane.showInputDialog("Entrez l'ID de l'utilisateur à supprimer :"));
                    utilisateurController.supprimerUtilisateur(idSuppression);
                    JOptionPane.showMessageDialog(null, "Utilisateur supprimé avec succès !");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Choix invalide !");
            }
        }
    }

				    // ======== MÉTHODE : Gérer les emprunts ========
				    private static void gererEmprunts(LivreController livreController, UtilisateurController utilisateurController,
				            EmpruntController empruntController) {
				while (true) {
				String choix = JOptionPane.showInputDialog(null,
				"=== Gestion des Emprunts ===\n" +
				  "1. Afficher tous les emprunts\n" +
				  "2. Enregistrer un nouvel emprunt\n" +
				  "3. Enregistrer un retour d'emprunt\n" +
				  "4. Retour au menu principal\n\n" +
				  "Veuillez entrer votre choix :");
				
				if (choix == null || choix.equals("4")) {
				return; // Retour au menu principal
				}
					
					switch (choix) {
					case "1": // Afficher tous les emprunts
					List<Emprunt> emprunts = empruntController.getEmprunts();
					StringBuilder affichage = new StringBuilder("=== Liste des Emprunts ===\n");
					for (Emprunt emprunt : emprunts) {
					affichage.append("ID: ").append(emprunt.getId())
					      .append(", UtilisateurID: ").append(emprunt.getUtilisateurID())
					      .append(", LivreID: ").append(emprunt.getLivreID())
					      .append(", Date Emprunt: ").append(emprunt.getDateEmprunt())
					      .append(", Retour Prévu: ").append(emprunt.getDateRetourPrevue())
					      .append(", Retour Effectif: ")
					      .append(emprunt.getDateRetourEffective() == null ? "En cours" : emprunt.getDateRetourEffective())
					      .append("\n");
				}
				JOptionPane.showMessageDialog(null, affichage.toString());
				break;
				
				case "2": // Enregistrer un nouvel emprunt
				List<Utilisateur> utilisateurs = utilisateurController.getUtilisateurs();
				List<Livre> livres = livreController.getLivres();
				
				String[] nomsUtilisateurs = utilisateurs.stream().map(Utilisateur::getNom).toArray(String[]::new);
				String[] titresLivres = livres.stream().map(Livre::getTitre).toArray(String[]::new);
				
				String nomUtilisateur = (String) JOptionPane.showInputDialog(null, "Sélectionnez un utilisateur :",
				  "Nouvel Emprunt", JOptionPane.QUESTION_MESSAGE, null, nomsUtilisateurs, nomsUtilisateurs[0]);
				
				String titreLivre = (String) JOptionPane.showInputDialog(null, "Sélectionnez un livre :",
				  "Nouvel Emprunt", JOptionPane.QUESTION_MESSAGE, null, titresLivres, titresLivres[0]);
				
				String dateRetour = JOptionPane.showInputDialog("Entrez la date de retour prévue (AAAA-MM-JJ) :");
				
				Utilisateur utilisateur = utilisateurController.findByName(nomUtilisateur);
				Livre livre = livres.stream().filter(l -> l.getTitre().equals(titreLivre)).findFirst().orElse(null);
				
				if (utilisateur != null && livre != null && dateRetour != null) {
				empruntController.enregistrerEmprunt(utilisateur, livre, LocalDate.parse(dateRetour));
				JOptionPane.showMessageDialog(null, "Emprunt enregistré avec succès !");
				} else {
				JOptionPane.showMessageDialog(null, "Erreur : Veuillez remplir correctement tous les champs.");
				}
				break;
				
				case "3": // Enregistrer un retour d'emprunt
				int idEmprunt = Integer.parseInt(JOptionPane.showInputDialog("Entrez l'ID de l'emprunt à retourner :"));
				empruntController.enregistrerRetour(idEmprunt);
				JOptionPane.showMessageDialog(null, "Retour enregistré avec succès !");
				break;
				
				default:
				JOptionPane.showMessageDialog(null, "Choix invalide !");
				}
				}
				}

}
