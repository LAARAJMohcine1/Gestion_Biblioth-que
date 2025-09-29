package src;

import controller.LivreController;
import controller.UtilisateurController;
import controller.EmpruntController;
import model.Livre;
import model.Utilisateur;
import model.Emprunt;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class MainFrame extends JFrame {
    private LivreController livreController;
    private UtilisateurController utilisateurController;
    private EmpruntController empruntController;

    private JTable tableLivres, tableUtilisateurs, tableEmprunts;

    public MainFrame() {
        livreController = new LivreController();
        utilisateurController = new UtilisateurController();
        empruntController = new EmpruntController();

        setTitle("Gestion de Bibliothèque");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Ajouter le logo principal
        try {
            ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("logo.jpg"));
            setIconImage(logo.getImage());
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement du logo : " + e.getMessage());
        }

        // Onglets principaux
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Livres", loadIcon("book_icon.png"), createLivresPanel(), "Gérer les livres");
        tabbedPane.addTab("Utilisateurs", loadIcon("user_icon.png"), createUtilisateursPanel(), "Gérer les utilisateurs");
        tabbedPane.addTab("Emprunts", loadIcon("loan_icon.png"), createEmpruntsPanel(), "Gérer les emprunts");

        add(tabbedPane);
    }

    // ===================== Onglet Livres =====================
    private JPanel createLivresPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] columnNames = {"ID", "Titre", "Auteur", "Année", "Genre"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        tableLivres = new JTable(model);
        loadLivresData(model);

        panel.add(new JScrollPane(tableLivres), BorderLayout.CENTER);

        // Boutons
        JPanel buttonPanel = new JPanel();
        JButton btnAjouter = new JButton("Ajouter Livre");
        JButton btnSupprimer = new JButton("Supprimer Livre");

        buttonPanel.add(btnAjouter);
        buttonPanel.add(btnSupprimer);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        btnAjouter.addActionListener(e -> {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID :"));
            String titre = JOptionPane.showInputDialog("Titre :");
            String auteur = JOptionPane.showInputDialog("Auteur :");
            int annee = Integer.parseInt(JOptionPane.showInputDialog("Année :"));
            String genre = JOptionPane.showInputDialog("Genre :");

            Livre livre = new Livre(id, titre, auteur, annee, genre);
            livreController.ajouterLivre(livre);
            loadLivresData(model);
        });

        btnSupprimer.addActionListener(e -> {
            int row = tableUtilisateurs.getSelectedRow(); // Récupérer la ligne sélectionnée

            if (row >= 0) { // Vérifie qu'une ligne est sélectionnée
                try {
                    Object value = tableUtilisateurs.getValueAt(row, 0); // Récupère l'ID
                    if (value != null && value.toString().trim().matches("\\d+")) { // Vérifie si l'ID est valide
                        int id = Integer.parseInt(value.toString().trim());
                        utilisateurController.supprimerUtilisateur(id);
                        loadUtilisateursData((DefaultTableModel) tableUtilisateurs.getModel()); // Recharger la table
                        JOptionPane.showMessageDialog(this, "Utilisateur supprimé avec succès !");
                    } else {
                        JOptionPane.showMessageDialog(this, "Erreur : L'ID sélectionné est vide ou invalide.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Erreur : Impossible de traiter l'ID sélectionné.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner un utilisateur à supprimer.");
            }
        });


        return panel;
    }

    // ===================== Onglet Utilisateurs =====================
    private JPanel createUtilisateursPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] columnNames = {"ID", "Nom", "Rôle"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        tableUtilisateurs = new JTable(model);
        loadUtilisateursData(model);

        panel.add(new JScrollPane(tableUtilisateurs), BorderLayout.CENTER);

        // Boutons
        JPanel buttonPanel = new JPanel();
        JButton btnAjouter = new JButton("Ajouter Utilisateur");
        JButton btnSupprimer = new JButton("Supprimer Utilisateur");

        buttonPanel.add(btnAjouter);
        buttonPanel.add(btnSupprimer);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        btnAjouter.addActionListener(e -> {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID :"));
            String nom = JOptionPane.showInputDialog("Nom :");
            String role = JOptionPane.showInputDialog("Rôle (Admin/Membre) :");

            Utilisateur user = new Utilisateur(id, nom, role);
            utilisateurController.ajouterUtilisateur(user);
            loadUtilisateursData(model);
        });

        btnSupprimer.addActionListener(e -> {
            int row = tableUtilisateurs.getSelectedRow();
            if (row != -1) {
                try {
                    // Récupérer l'ID de l'utilisateur (en ignorant les en-têtes)
                    int id = Integer.parseInt(tableUtilisateurs.getValueAt(row, 0).toString());
                    utilisateurController.supprimerUtilisateur(id);
                    loadUtilisateursData(model);
                    JOptionPane.showMessageDialog(this, "Utilisateur supprimé avec succès.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Erreur : ID invalide sélectionné.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Sélectionnez un utilisateur à supprimer.");
            }
        });


        return panel;
    }

    // ===================== Onglet Emprunts =====================
    private JPanel createEmpruntsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] columnNames = {"ID", "Utilisateur", "Livre", "Date Emprunt", "Retour Prévu"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        tableEmprunts = new JTable(model);
        loadEmpruntsData(model);

        panel.add(new JScrollPane(tableEmprunts), BorderLayout.CENTER);

        // Boutons
        JPanel buttonPanel = new JPanel();
        JButton btnAjouter = new JButton("Enregistrer Emprunt");
        JButton btnSupprimer = new JButton("Supprimer Emprunt");

        buttonPanel.add(btnAjouter);
        buttonPanel.add(btnSupprimer);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        btnAjouter.addActionListener(e -> {
            // Sélectionner utilisateur
            List<Utilisateur> utilisateurs = utilisateurController.getUtilisateurs();
            String[] userNames = utilisateurs.stream().map(Utilisateur::getNom).toArray(String[]::new);
            String nomUtilisateur = (String) JOptionPane.showInputDialog(null, "Choisissez un utilisateur :", "Enregistrer Emprunt", JOptionPane.QUESTION_MESSAGE, null, userNames, userNames[0]);

            // Sélectionner livre
            List<Livre> livres = livreController.getLivres();
            String[] bookTitles = livres.stream().map(Livre::getTitre).toArray(String[]::new);
            String titreLivre = (String) JOptionPane.showInputDialog(null, "Choisissez un livre :", "Enregistrer Emprunt", JOptionPane.QUESTION_MESSAGE, null, bookTitles, bookTitles[0]);

            String dateRetour = JOptionPane.showInputDialog("Date de retour prévue (AAAA-MM-JJ) :");

            Utilisateur utilisateur = utilisateurController.findByName(nomUtilisateur);
            Livre livre = livreController.rechercherLivres("Titre", titreLivre).get(0);
            empruntController.enregistrerEmprunt(utilisateur, livre, LocalDate.parse(dateRetour));
            loadEmpruntsData(model);
        });

        btnSupprimer.addActionListener(e -> {
            int row = tableEmprunts.getSelectedRow();
            if (row != -1) {
                int id = (int) tableEmprunts.getValueAt(row, 0);
                empruntController.supprimerEmprunt(id);
                loadEmpruntsData(model);
            } else {
                JOptionPane.showMessageDialog(this, "Sélectionnez un emprunt à supprimer.");
            }
        });

        return panel;
    }

    // ===================== Chargement des données =====================
    private void loadLivresData(DefaultTableModel model) {
        model.setRowCount(0);
        for (Livre livre : livreController.getLivres()) {
            model.addRow(new Object[]{livre.getId(), livre.getTitre(), livre.getAuteur(), livre.getAnneePublication(), livre.getGenre()});
        }
    }

    private void loadUtilisateursData(DefaultTableModel model) {
        model.setRowCount(0);
        for (Utilisateur user : utilisateurController.getUtilisateurs()) {
            model.addRow(new Object[]{user.getId(), user.getNom(), user.getRole()});
        }
    }

    private void loadEmpruntsData(DefaultTableModel model) {
        model.setRowCount(0);
        for (Emprunt emprunt : empruntController.getEmprunts()) {
            model.addRow(new Object[]{emprunt.getId(), emprunt.getUtilisateurID(), emprunt.getLivreID(),
                    emprunt.getDateEmprunt(), emprunt.getDateRetourPrevue()});
        }
    }

    private ImageIcon loadIcon(String path) {
        return new ImageIcon(getClass().getClassLoader().getResource(path));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
