package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LivreView extends JFrame {
    // Déclaration des composants
    private JButton btnAjouterLivre;
    private JButton btnModifierLivre;
    private JButton btnSupprimerLivre;
    private JButton btnRechercherLivre;
    private JTextField txtRecherche;
    private JComboBox<String> comboCritere;
    private JTable tableLivres;

    public LivreView() {
        // Configuration de la fenêtre
        setTitle("Gestion des Livres");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ======== Haut : Formulaire de recherche ========
        JPanel panelRecherche = new JPanel();
        txtRecherche = new JTextField(20);
        comboCritere = new JComboBox<>(new String[]{"Titre", "Auteur", "Genre"});
        btnRechercherLivre = new JButton("Rechercher");

        panelRecherche.add(new JLabel("Rechercher :"));
        panelRecherche.add(txtRecherche);
        panelRecherche.add(new JLabel("Critère :"));
        panelRecherche.add(comboCritere);
        panelRecherche.add(btnRechercherLivre);

        add(panelRecherche, BorderLayout.NORTH);

        // ======== Centre : Tableau des livres ========
        String[] colonnes = {"Titre", "Auteur", "Année", "Genre"};
        Object[][] donnees = {}; // Données initialement vides
        tableLivres = new JTable(donnees, colonnes);
        JScrollPane scrollPane = new JScrollPane(tableLivres);

        add(scrollPane, BorderLayout.CENTER);

        // ======== Bas : Boutons de gestion ========
        JPanel panelBoutons = new JPanel();
        btnAjouterLivre = new JButton("Ajouter Livre");
        btnModifierLivre = new JButton("Modifier Livre");
        btnSupprimerLivre = new JButton("Supprimer Livre");

        panelBoutons.add(btnAjouterLivre);
        panelBoutons.add(btnModifierLivre);
        panelBoutons.add(btnSupprimerLivre);

        add(panelBoutons, BorderLayout.SOUTH);

        // Rendre la fenêtre visible
        setVisible(true);
    }

    // ======== Méthodes pour ajouter des écouteurs ========
    public void addAjouterLivreListener(ActionListener listener) {
        btnAjouterLivre.addActionListener(listener);
    }

    public void addModifierLivreListener(ActionListener listener) {
        btnModifierLivre.addActionListener(listener);
    }

    public void addSupprimerLivreListener(ActionListener listener) {
        btnSupprimerLivre.addActionListener(listener);
    }

    public void addRechercherLivreListener(ActionListener listener) {
        btnRechercherLivre.addActionListener(listener);
    }

    // ======== Méthodes pour accéder aux données ========
    public String getRechercheTexte() {
        return txtRecherche.getText(); // Texte saisi dans le champ de recherche
    }

    public String getCritereRecherche() {
        return (String) comboCritere.getSelectedItem(); // Critère sélectionné
    }

    public JTable getTableLivres() {
        return tableLivres; // Retourne le tableau des livres
    }

    public void setTableData(Object[][] donnees) {
        // Mise à jour des données du tableau
        String[] colonnes = {"Titre", "Auteur", "Année", "Genre"};
        tableLivres.setModel(new javax.swing.table.DefaultTableModel(donnees, colonnes));
    }
}
